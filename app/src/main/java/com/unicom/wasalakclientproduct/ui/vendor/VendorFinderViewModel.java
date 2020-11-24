package com.unicom.wasalakclientproduct.ui.vendor;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.VendorModel;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.repository.LocationRepository;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.SingleLiveData;

import java.io.IOException;
import java.lang.annotation.Annotation;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

@FragmentScoped
public class VendorFinderViewModel extends ViewModel {
    private final CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository repository;
    private Context context;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    private LocationRepository locationRepository;
    private PreferenceUtils preference;

    private MutableLiveData<UserLocation> userLocationMutableLiveData;

    private MutableLiveData<Resource<VendorModel>> vendorMutableLiveData;

    @ViewModelInject
    public VendorFinderViewModel(@Assisted SavedStateHandle savedStateHandle
            , @ActivityContext Context context,
                                 KeyboardUtils keyboardUtils,
                                 UserRepository repository,
                                 LocationRepository locationRepository,
                                 PreferenceUtils preferenceUtils,
                                 Retrofit retrofit) {

        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.repository = repository;
        this.retrofit = retrofit;
        this.preference=preferenceUtils;
        this.locationRepository = locationRepository;
        composite = new CompositeDisposable();
    }


    public MutableLiveData<UserLocation> observeUserLocation() {
        if(userLocationMutableLiveData==null)
            userLocationMutableLiveData = new MutableLiveData<>();
        return userLocationMutableLiveData;
    }

    public MutableLiveData<Resource<VendorModel>> observeVendors( int marketType,
                                                                  String latitude,
                                                                  String longitude,
                                                                  int distance) {
        if(vendorMutableLiveData==null) {
            vendorMutableLiveData = new MutableLiveData<>();
            searchNearestVendor(marketType , latitude , longitude , distance);
        }
        return vendorMutableLiveData;
    }

    public void searchNearestVendor(
            int marketType,
            String latitude,
            String longitude,
            int distance

    ) {
        if (composite != null) {
            composite.clear();
        }
        keyboardUtils.hideKeyboard();
        vendorMutableLiveData.setValue(Resource.loading(null));
        composite.add(repository.getVendors(Constants.BEARER+ preference.getTokenUser(),marketType, longitude, latitude, distance == 0 ? null : distance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));


    }

    void requestUserCurrentLocation() {
        composite.add(locationRepository.getLastLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLocationResults));

    }

    private void handleLocationError(Throwable throwable) {
        vendorMutableLiveData.setValue(Resource.error("Error", null));
    }

    private void handleLocationResults(UserLocation userLocation) {
        if (userLocation != null) {
            userLocationMutableLiveData.setValue(userLocation);
        }
    }


    private void handleError(Throwable t) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    vendorMutableLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            vendorMutableLiveData.setValue(Resource.error(t.getMessage().toString(), null));
        }

    }

    private void handleResults(VendorModel object) {
        if (object != null && object.getSuccess()) {
            vendorMutableLiveData.setValue(Resource.success(object));
        } else {
            vendorMutableLiveData.setValue(Resource.error(object.getError().getMessage().toString(), null));
        }

    }


}

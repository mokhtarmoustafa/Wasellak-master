package com.unicom.wasalakclientproduct.viewmodel.user;

import android.content.Context;
import android.widget.Toast;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.guest.RegisterModel;
import com.unicom.wasalakclientproduct.model.user.MarketPlaceTypeModel;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;

import dagger.hilt.android.qualifiers.ActivityContext;
import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

public class MarketsViewModel extends ViewModel {
    private Context context;
    private SavedStateHandle savedStateHandle;
    private UserRepository userRepository;
    private PreferenceUtils preference;
    private Retrofit retrofit;
    private KeyboardUtils keyboardUtils;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Resource<MarketPlaceTypeModel>> marketPlaceTypeLiveData;
    private CompositeDisposable disposables = new CompositeDisposable();
    public MutableLiveData<String> name = new MutableLiveData<>();

    @ViewModelInject
    public MarketsViewModel(@Assisted SavedStateHandle savedStateHandle, @ActivityContext Context context, KeyboardUtils keyboardUtils, UserRepository userRepository, PreferenceUtils preference, Retrofit retrofit) {
        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.userRepository = userRepository;
        this.preference = preference;
        this.retrofit = retrofit;
        this.keyboardUtils = keyboardUtils;
    }

    public LiveData<Resource<MarketPlaceTypeModel>> getMarketPlaceTypeLiveData(String name, String sorting, Integer skipCount, Integer maxCount) {
        if (marketPlaceTypeLiveData == null) {
            marketPlaceTypeLiveData = new MutableLiveData<>();
            getMarketPlaceType(name, sorting, skipCount, maxCount);
        }
        return marketPlaceTypeLiveData;
    }

    public void getMarketPlaceType(String name, String sorting, Integer skipCount, Integer maxCount) {
        keyboardUtils.hideKeyboard();
//        isLoading.setValue(true);
        disposables.add(userRepository.getMarketPlaceType(Constants.BEARER+ preference.getTokenUser(), name, sorting, skipCount, maxCount).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleSuccess, this::handleError));
    }

    private void handleError(Throwable throwable) {
        try {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, RegisterModel> errorConverter =
                        retrofit.responseBodyConverter(RegisterModel.class, new Annotation[0]);
                try {
                    RegisterModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    marketPlaceTypeLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            marketPlaceTypeLiveData.setValue(Resource.error(e.getMessage(), null));
        }

//        isLoading.setValue(false);
    }

    private void handleSuccess(MarketPlaceTypeModel marketPlaceTypeModel) {
        if (marketPlaceTypeModel != null && marketPlaceTypeModel.getSuccess()) {
//            marketPlaceTypeLiveData.setValue(marketPlaceTypeModel);
            marketPlaceTypeLiveData.setValue(Resource.success(marketPlaceTypeModel));

        } else
            marketPlaceTypeLiveData.setValue(Resource.error(marketPlaceTypeModel.getError().getMessage().toString(), null));
//            marketPlaceTypeLiveData.setValue(null);
//        isLoading.setValue(false);
    }


}

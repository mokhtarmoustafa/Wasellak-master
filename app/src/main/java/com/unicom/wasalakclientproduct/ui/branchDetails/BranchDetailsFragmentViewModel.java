package com.unicom.wasalakclientproduct.ui.branchDetails;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.repository.LocationRepository;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.utils.SingleLiveData;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

@FragmentScoped
public class BranchDetailsFragmentViewModel extends ViewModel {

    //region Members
    private final CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository repository;
    private Context context;
    private PreferenceUtils preference;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    private LocationRepository locationRepository;

    private MutableLiveData<UserLocation> userLocationMutableLiveData = new MutableLiveData<>();

    public LiveData<UserLocation> observeUserLocation() {
        return userLocationMutableLiveData;
    }


    //endregion

    //region Events
    @ViewModelInject
    public BranchDetailsFragmentViewModel(@Assisted SavedStateHandle savedStateHandle
            , @ActivityContext Context context,
                                          KeyboardUtils keyboardUtils,
                                          UserRepository repository,
                                          LocationRepository locationRepository,
                                          PreferenceUtils preference,
                                          Retrofit retrofit) {

        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.repository = repository;
        this.retrofit = retrofit;
        this.locationRepository = locationRepository;
        this.preference = preference;
        composite = new CompositeDisposable();
    }

    //endregion

    //region Helper Functions
    void requestUserCurrentLocation() {

        composite.add(locationRepository.getLastLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLocationResults, this::handleLocationError));

    }

    private void handleLocationError(Throwable throwable) {
        userLocationMutableLiveData.setValue(null);
    }

    private void handleLocationResults(UserLocation userLocation) {
        if (userLocation != null) {
            userLocationMutableLiveData.setValue(userLocation);
        }
    }


    //endregion
}

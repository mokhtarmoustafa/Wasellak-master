package com.unicom.wasalakclientproduct.viewmodel.user;

import android.content.Context;
import android.widget.Toast;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.StructueMode;
import com.unicom.wasalakclientproduct.model.user.ChangePassUser;
import com.unicom.wasalakclientproduct.reciever.NetworkReceiver;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.FragmentScoped;
import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

@FragmentScoped
public class ChangePasswordViewModel extends ViewModel {
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> passwordNew = new MutableLiveData<>();
    public MutableLiveData<String> passwordConfirm = new MutableLiveData<>();
    private MutableLiveData<ChangePassUser> userMutableLiveData;
    private MutableLiveData<Resource<StructueMode>> changePassModelMutableLiveData;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public PublishSubject publishSubject = PublishSubject.create();
    public MutableLiveData<Boolean> enableButton = new MutableLiveData<>();
    private Retrofit retrofit;
    private Context context;
    private Disposable disposable;
    private UserRepository userRepository;
    private PreferenceUtils preference;
    private KeyboardUtils keyboardUtils;
    private ChangePassUser changePassUser;
    private SavedStateHandle savedStateHandle;

    @ViewModelInject
    public ChangePasswordViewModel(@Assisted SavedStateHandle savedStateHandle, @ActivityContext Context context, UserRepository userRepository, PreferenceUtils preference, KeyboardUtils keyboardUtils, Retrofit retrofit) {
        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.userRepository = userRepository;
        this.preference = preference;
        this.keyboardUtils = keyboardUtils;
        isLoading.setValue(false);
        enableButton.setValue(true);
        this.retrofit = retrofit;
    }

    public MutableLiveData<ChangePassUser> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onChangeClick() {
        enableButton.setValue(false);
        keyboardUtils.hideKeyboard();

        changePassUser = new ChangePassUser(password.getValue(), passwordNew.getValue(), passwordConfirm.getValue());
        userMutableLiveData.setValue(changePassUser);
    }

    public MutableLiveData<Resource<StructueMode>> getLChangePassNetworkResponse() {
        if (changePassModelMutableLiveData == null) {
            changePassModelMutableLiveData = new MutableLiveData<>();
        }
        enableButton.setValue(true);
        isLoading.setValue(false);
        return changePassModelMutableLiveData;
    }


    public void changePass() {
        isLoading.setValue(true);

        disposable = userRepository.getChangePass(Constants.BEARER + preference.getTokenUser(), changePassUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResults, this::handleError);
    }

    private void handleError(Throwable t) {
        StructueMode error = null;
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, StructueMode> errorConverter =
                        retrofit.responseBodyConverter(StructueMode.class, new Annotation[0]);
                try {
                    error = errorConverter.convert(((HttpException) t).response().errorBody());
//                        Toasty.error(context, error.getError().getMessage(), Toast.LENGTH_LONG).show();
                    changePassModelMutableLiveData.setValue(Resource.error(error.getError().getMessage(), null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            changePassModelMutableLiveData.setValue(Resource.error(e.getMessage(), null));
        }
        isLoading.setValue(false);
        enableButton.setValue(true);
    }

    private void handleResults(StructueMode changePassModel) {
        if ((changePassModel) != null && changePassModel.getSuccess()) {
            changePassModelMutableLiveData.setValue(Resource.success(changePassModel));
        } else {
            changePassModelMutableLiveData.setValue(Resource.error(changePassModel.getError().getMessage(), null));
        }
        isLoading.setValue(false);
        enableButton.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.dispose();
        }

    }
}
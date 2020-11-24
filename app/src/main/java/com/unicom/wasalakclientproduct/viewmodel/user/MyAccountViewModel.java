package com.unicom.wasalakclientproduct.viewmodel.user;

import android.content.Context;
import android.widget.Toast;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.StructueMode;
import com.unicom.wasalakclientproduct.model.user.AccountModel;
import com.unicom.wasalakclientproduct.model.user.LanguageClass;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.FragmentScoped;
import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

@FragmentScoped
public class MyAccountViewModel extends ViewModel {
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Resource<AccountModel>> accountMutableLiveData;
    private MutableLiveData<StructueMode> langMutableLiveData;
    private CompositeDisposable disposables = new CompositeDisposable();
    public MutableLiveData<Boolean> notification = new MutableLiveData<>();
    public PublishSubject publishSubject = PublishSubject.create();
    public MutableLiveData<String> langLiveData;
    private Retrofit retrofit;
    private Context context;
    private SavedStateHandle savedStateHandle;
    private UserRepository userRepository;
    private PreferenceUtils preference;

    @ViewModelInject
    public MyAccountViewModel(@Assisted SavedStateHandle savedStateHandle, @ActivityContext Context context, UserRepository userRepository, PreferenceUtils preference, Retrofit retrofit) {
        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.userRepository = userRepository;
        this.preference = preference;
        this.retrofit = retrofit;
    }

    public MutableLiveData<Resource<AccountModel>> getAccountMutableLiveData() {
        if (accountMutableLiveData == null) {
            accountMutableLiveData = new MutableLiveData<>();
        }
        retrieveAccount();
        return accountMutableLiveData;
    }

    private void retrieveAccount() {
        isLoading.setValue(true);
        Disposable disposable = userRepository.getAccount(Constants.BEARER + preference.getTokenUser()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResults, this::handleError);

        disposables.add(disposable);
    }

    private void handleResults(AccountModel accountModel) {
        if (accountModel != null && accountModel.getSuccess()) {
            accountMutableLiveData.setValue(Resource.success(accountModel));
            preference.saveUserdata(accountModel);
        }
        else {
            accountMutableLiveData.setValue(Resource.error(accountModel.getError().getMessage(), null));
        }
//        isLoading.setValue(false);
    }

    private void handleError(Throwable t) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, StructueMode> errorConverter =
                        retrofit.responseBodyConverter(StructueMode.class, new Annotation[0]);
                try {
                    StructueMode error = errorConverter.convert(((HttpException) t).response().errorBody());
                    accountMutableLiveData.setValue(Resource.error(error.getError().getMessage().toString(), null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            accountMutableLiveData.setValue(Resource.error(e.getMessage().toString(), null));
        }
        isLoading.setValue(false);
    }


    public MutableLiveData<StructueMode> getLangMutableLiveData() {
        if (langMutableLiveData == null) {
            langMutableLiveData = new MutableLiveData<>();
        }
        return langMutableLiveData;
    }

    public MutableLiveData<String> getLang() {
        if (langLiveData == null) {
            langLiveData = new MutableLiveData<>();
        }
        return langLiveData;
    }

    public void langService(String lang) {
        isLoading.setValue(true);
        disposables.add(userRepository.getLang(Constants.BEARER + preference.getTokenUser(), new LanguageClass(lang)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResultsLanguage, this::handleError));
    }

    private void handleResultsLanguage(StructueMode structueMode) {
        if (structueMode != null && structueMode.getSuccess()) {
            langMutableLiveData.setValue(structueMode);
        }
        isLoading.setValue(false);
    }


//    public void onChangeNotification() {
//        disposables.add(publishSubject.switchMap(new Function() {
//            @Override
//            public Object apply(Object o) throws Throwable {
//                isLoading.setValue(true);
//                return userRepository.getChangeNotification(Constants.BEARER + preference.getTokenUser(), new NotificationUser(notification.getValue(), preference.getTokenDevice()))
//                        .subscribeOn(Schedulers.io());
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResultsNotifi, this::handleErrorNotifi));
//    }

    private void handleErrorNotifi(Object o) {
        Toasty.error(context, ((Throwable) o).getMessage(), Toast.LENGTH_SHORT).show();
        isLoading.setValue(false);
    }

//    private void handleResultsNotifi(Object o) {
//        if (((NotificationChangeModel) o).getIsSuccess()) {
//            preference.saveNotificationState(notification.getValue());
//        } else {
//            Toasty.error(context, (((NotificationChangeModel) o).getMessage()), Toast.LENGTH_SHORT).show();
//        }
//        isLoading.setValue(false);
//    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}

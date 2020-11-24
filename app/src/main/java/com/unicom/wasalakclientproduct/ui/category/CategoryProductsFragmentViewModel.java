package com.unicom.wasalakclientproduct.ui.category;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.branch.BranchResponse;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
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
public class CategoryProductsFragmentViewModel extends ViewModel {

    //region Members
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private KeyboardUtils keyboardUtils;
    private SavedStateHandle savedStateHandle;
    private UserRepository userRepository;
    private PreferenceUtils preference;
    private Retrofit retrofit;
    private final CompositeDisposable composite;
    public MutableLiveData<Boolean> isLoading = new SingleLiveData<>();
    private MutableLiveData<Resource<BranchResponse>> branchResponse;
    private SingleLiveData<Resource<CartModel>> addToCartResponse;

    //endregion

    //region Events
    @ViewModelInject
    public CategoryProductsFragmentViewModel(@Assisted SavedStateHandle savedStateHandle, @ActivityContext Context context,
                                             KeyboardUtils keyboardUtils, UserRepository userRepository, PreferenceUtils preference, Retrofit retrofit) {
        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.userRepository = userRepository;
        this.preference = preference;
        this.retrofit = retrofit;
        composite = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }
    //endregion
    //region Helper Functions

    public LiveData<Resource<BranchResponse>> getBranchDetailsLiveData(Integer branchId, String latitude, String longitude) {
        if (branchResponse == null) {
            branchResponse = new MutableLiveData<>();
            getBranchDetails(Constants.BEARER + preference.getTokenUser(), branchId, latitude, longitude);
        }
        return branchResponse;
    }

    public SingleLiveData<Resource<CartModel>> getAddToCartResponse() {
        if (addToCartResponse == null) {
            addToCartResponse = new SingleLiveData<>();
        }
        return addToCartResponse;
    }

    public void addToCart(AddToCartDTO addToCartDTO) {
        keyboardUtils.hideKeyboard();
        addToCartResponse.setValue(Resource.loading(null));
        composite.add(userRepository.getAddToCart(Constants.BEARER + " " + preference.getTokenUser(), addToCartDTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAddToCartResults, this::handleErrorAddToCart));
    }

    private void handleErrorAddToCart(Throwable throwable) {
        try {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    addToCartResponse.setValue(Resource.error(error.getError().getMessage().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            addToCartResponse.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    private void handleAddToCartResults(CartModel cartOperationsResponse) {
        if (cartOperationsResponse != null && cartOperationsResponse.getSuccess())
            this.addToCartResponse.setValue(Resource.success(cartOperationsResponse));
        else
            this.addToCartResponse.setValue(Resource.error(cartOperationsResponse.getError().getDetails().toString(), null));
    }

    private void getBranchDetails(
            String token, Integer branchId, String latitude, String longitude

    ) {

        keyboardUtils.hideKeyboard();
        branchResponse.setValue(Resource.loading(null));
        composite.add(userRepository.getBranchDetails(token, branchId, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));


    }


    private void handleError(Throwable t) {
        try {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    branchResponse.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            branchResponse.setValue(Resource.error(t.getMessage(), null));
        }
    }

    private void handleResults(BranchResponse object) {

        if (object != null && object.getSuccess()) {
            branchResponse.setValue(Resource.success(object));
        } else {
            branchResponse.setValue(Resource.error(object.getError().getMessage().toString(), null));
        }
    }


    //endregion


}

package com.unicom.wasalakclientproduct.ui.productDetails;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;
import com.unicom.wasalakclientproduct.repository.LocationRepository;
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
public class ProductDetailViewModel extends ViewModel {

    //region Members
    private final CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository userRepository;
    private Context context;
    private PreferenceUtils preference;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    private SingleLiveData<Resource<ProductResponse>> productResponseMutableLiveData;
    private SingleLiveData<com.unicom.wasalakclientproduct.utils.Resource<CartModel>> addToCartResponse;

    public SingleLiveData<Resource<ProductResponse>> observeProductDetailsLiveData(int id) {
        if (productResponseMutableLiveData == null)
            productResponseMutableLiveData = new SingleLiveData<>();
        getProductDetails(id);
        return productResponseMutableLiveData;
    }
    public SingleLiveData<com.unicom.wasalakclientproduct.utils.Resource<CartModel>> getAddToCartResponse() {
        if (addToCartResponse == null) {
            addToCartResponse = new SingleLiveData<>();
        }
        return addToCartResponse;
    }
    //endregion

    //region Events

    @ViewModelInject
    public ProductDetailViewModel(@Assisted SavedStateHandle savedStateHandle
            , @ActivityContext Context context,
                                  KeyboardUtils keyboardUtils,
                                  UserRepository repository,
                                  LocationRepository locationRepository,
                                  PreferenceUtils preference,
                                  Retrofit retrofit) {

        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.userRepository = repository;
        this.retrofit = retrofit;
        this.preference = preference;
        composite = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }
    //endregion

    //region Helper Functions
    private void getProductDetails(int productId) {
        keyboardUtils.hideKeyboard();
        productResponseMutableLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.getProductDetail(Constants.BEARER + preference.getTokenUser(), productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));
    }

    public void addToCart(AddToCartDTO addToCartDTO) {
        keyboardUtils.hideKeyboard();
        addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.loading(null));
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
                    addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.error(error.getError().getMessage().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.error(throwable.getMessage(), null));
        }
    }

    private void handleAddToCartResults(CartModel cartOperationsResponse) {
        if (cartOperationsResponse != null && cartOperationsResponse.getSuccess())
            this.addToCartResponse.setValue(Resource.success(cartOperationsResponse));
        else
            this.addToCartResponse.setValue(Resource.error(cartOperationsResponse.getError().getDetails().toString(), null));
    }


    private void handleError(Throwable throwable) {
        try {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    productResponseMutableLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            productResponseMutableLiveData.setValue(Resource.error(e.getMessage(), null));
        }
    }

    private void handleResults(ProductResponse productData) {
        if (productData != null && productData.getmSuccess()) {
            productResponseMutableLiveData.setValue(Resource.success(productData));
        } else {
            productResponseMutableLiveData.setValue(Resource.error(productData.getError().getMessage().toString(), null));
        }
    }
    //endregion
}

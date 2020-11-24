package com.unicom.wasalakclientproduct.ui.product_list;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.category.ProductVendorResponse;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.ui.vendor.Resource;
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
public class ProductVendorListViewModel extends ViewModel {
    private final CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository repository;
    private Context context;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    private SingleLiveData<com.unicom.wasalakclientproduct.utils.Resource<CartModel>> addToCartResponse;
    private PreferenceUtils preference;


    private MutableLiveData<Resource<ProductVendorResponse>> productMutableLiveData;

    @ViewModelInject
    public ProductVendorListViewModel(@Assisted SavedStateHandle savedStateHandle
            , @ActivityContext Context context,
                                      KeyboardUtils keyboardUtils,
                                      UserRepository repository,
                                      Retrofit retrofit
            , PreferenceUtils preference) {

        this.savedStateHandle = savedStateHandle;
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.repository = repository;
        this.retrofit = retrofit;
        this.preference = preference;
        composite = new CompositeDisposable();

    }


    public MutableLiveData<Resource<ProductVendorResponse>> observeVendors(Boolean isVendorDeleted,
                                                                           Integer categoryId,
                                                                           String name,
                                                                           String creationTime,
                                                                           Integer status,
                                                                           String sorting,
                                                                           Integer skipCount,
                                                                           Integer maxResultCounte) {
        if (productMutableLiveData == null) {
            productMutableLiveData = new SingleLiveData<>();
            searchAllVendorProducts(isVendorDeleted , categoryId , name , creationTime , status , sorting , skipCount , maxResultCounte);
        }
        return productMutableLiveData;
    }

    public void searchAllVendorProducts(
            Boolean isVendorDeleted,
            Integer categoryId,
            String name,
            String creationTime,
            Integer status,
            String sorting,
            Integer skipCount,
            Integer maxResultCounte

    ) {

        if (composite != null) {
            composite.clear();
        }
        keyboardUtils.hideKeyboard();
        productMutableLiveData.setValue(Resource.loading(null));
        composite.add(repository.getAllProducts(Constants.BEARER + preference.getTokenUser(), false, categoryId, null, null, null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));


    }


    private void handleError(Throwable t) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    productMutableLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            productMutableLiveData.setValue(Resource.error(t.getMessage().toString(), null));
        }

    }

    private void handleResults(ProductVendorResponse object) {
        if (object != null) {
            productMutableLiveData.setValue(Resource.success(object));
        } else {
            productMutableLiveData.setValue(Resource.error("error", null));
        }

    }

    public SingleLiveData<com.unicom.wasalakclientproduct.utils.Resource<CartModel>> getAddToCartResponse() {
        if (addToCartResponse == null) {
            addToCartResponse = new SingleLiveData<>();
        }
        return addToCartResponse;
    }

    public void addToCart(AddToCartDTO addToCartDTO) {
        keyboardUtils.hideKeyboard();
        addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.loading(null));
        composite.add(repository.getAddToCart(Constants.BEARER + " " + preference.getTokenUser(), addToCartDTO)
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
            this.addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.success(cartOperationsResponse));
        else
            this.addToCartResponse.setValue(com.unicom.wasalakclientproduct.utils.Resource.error(cartOperationsResponse.getError().getDetails().toString(), null));
    }
}
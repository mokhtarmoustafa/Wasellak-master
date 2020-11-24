package com.unicom.wasalakclientproduct.ui.cartConfirm;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.cart.ConfirmOrderDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.ui.orderCreate.OrderCreateModel;
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
public class CartConfirmProductsViewModel extends ViewModel {
    //region Members
    private CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository userRepository;
    private Context context;
    private PreferenceUtils preference;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    public MutableLiveData<Resource<CartModel>> cartResponseLiveData;
    public SingleLiveData<Resource<OrderCreateModel>> createOrderLiveData;

    @ViewModelInject
    public CartConfirmProductsViewModel(@Assisted SavedStateHandle savedStateHandle
            , @ActivityContext Context context,
                         KeyboardUtils keyboardUtils,
                         UserRepository repository,
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

    public MutableLiveData<Resource<CartModel>> observeListProductToCart() {
        if (cartResponseLiveData == null) {
            cartResponseLiveData = new MutableLiveData<>();
            getListCartProduct();
        }
        return cartResponseLiveData;
    }

    private void getListCartProduct() {
        keyboardUtils.hideKeyboard();
        cartResponseLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.getDataToConfirmOrder(Constants.BEARER + preference.getTokenUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));
    }

    private void handleResults(CartModel cartModel) {
        if (cartModel != null && cartModel.getResult().getTotalProduct() > 0) {
            cartResponseLiveData.setValue(Resource.success(cartModel));
        } else {
            cartResponseLiveData.setValue(Resource.error("error", null));
        }
    }

    private void handleError(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    cartResponseLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            cartResponseLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    public SingleLiveData<Resource<OrderCreateModel>> observeCreateOrder(){
        if (createOrderLiveData == null){
            createOrderLiveData = new SingleLiveData<>();
        }
        return createOrderLiveData;
    }

    public void createOrder(){
        keyboardUtils.hideKeyboard();
        createOrderLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.confirmOrder(Constants.BEARER + preference.getTokenUser() , new ConfirmOrderDTO(1 , ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultsConfirmOrder, this::handleErrorConfirmOrder));
    }

    private void handleResultsConfirmOrder(OrderCreateModel orderCreateModel) {
        if (orderCreateModel!= null && orderCreateModel.getSuccess()){
            createOrderLiveData.setValue(Resource.success(orderCreateModel));
        }else {
            createOrderLiveData.setValue(Resource.error("Error", null));
        }
    }

    private void handleErrorConfirmOrder(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    createOrderLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            createOrderLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }
}

package com.unicom.wasalakclientproduct.ui.cart;

import android.content.Context;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.cart.ChangeQuantityCartDTO;
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
public class CartViewModel extends ViewModel {

    //region Members
    private CompositeDisposable composite;
    private KeyboardUtils keyboardUtils;
    private UserRepository userRepository;
    private Context context;
    private PreferenceUtils preference;
    private SavedStateHandle savedStateHandle;
    private Retrofit retrofit;
    public MutableLiveData<Resource<CartModel>> cartResponseLiveData;
    private SingleLiveData<Resource<CartModel>> cartChangeQuantityLiveData;
    private SingleLiveData<Resource<CartModel>> cartRemoveLiveData;
    private SingleLiveData<Resource<CartModel>> cartClearLiveData;
    private SingleLiveData<Resource<CartOperationsResponse>> cartChooseAddressLiveData;
    public MutableLiveData<Integer> addressId = new MutableLiveData<>();


    public MutableLiveData<Resource<CartModel>> observeListProductToCart() {
        if (cartResponseLiveData == null) {
            cartResponseLiveData = new MutableLiveData<>();
            getListCartProduct();
        }
        return cartResponseLiveData;
    }

    public SingleLiveData<Resource<CartModel>> observeCartChangeQuantityLiveData() {
        if (cartChangeQuantityLiveData == null) {
            cartChangeQuantityLiveData = new SingleLiveData<>();
        }
        return cartChangeQuantityLiveData;
    }

    public SingleLiveData<Resource<CartModel>> observeRemoveProductToCart() {
        if (cartRemoveLiveData == null) {
            cartRemoveLiveData = new SingleLiveData<>();
        }
        return cartRemoveLiveData;
    }

    public SingleLiveData<Resource<CartModel>> observeClearCart() {
        if (cartClearLiveData == null) {
            cartClearLiveData = new SingleLiveData<>();
        }
        return cartClearLiveData;
    }

    public SingleLiveData<Resource<CartOperationsResponse>> observeCartAddress() {
        if (cartChooseAddressLiveData == null) {
            cartChooseAddressLiveData = new SingleLiveData<>();
        }
        return cartChooseAddressLiveData;
    }

    private void getListCartProduct() {
        keyboardUtils.hideKeyboard();
        cartResponseLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.getMyCartDetail(Constants.BEARER + preference.getTokenUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));
    }

    private void handleResults(CartModel cartModel) {
        if (cartModel.getResult() != null && cartModel.getResult().getTotalProduct() > 0) {
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

    public void clearListCartProduct() {
        keyboardUtils.hideKeyboard();
        cartClearLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.clearMyCartDetail(Constants.BEARER + preference.getTokenUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleClearResults, this::handleClearError));
    }


    private void handleClearError(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    cartClearLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            cartClearLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    private void handleClearResults(CartModel cartModel) {
        if (cartModel != null && cartModel.getSuccess()) {
            preference.setCartCount(0);
            cartResponseLiveData.setValue(Resource.error("Error" , null));
        }else {
            cartClearLiveData.setValue(Resource.error("Error" , null));
        }
    }



    public void changeQuantity(int product_id, int quantity) {
        keyboardUtils.hideKeyboard();
        cartChangeQuantityLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.changeQuantity(Constants.BEARER + preference.getTokenUser(), new ChangeQuantityCartDTO(product_id, quantity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultsChangeQuantity, this::handleErrorOperationsChangeQuantity));
    }

    private void handleResultsChangeQuantity(CartModel cartModel) {
        if (cartModel != null && cartModel.getSuccess()) {
            cartChangeQuantityLiveData.setValue(Resource.success(cartModel));
        }else {
            cartChangeQuantityLiveData.setValue(Resource.error("Error" , null));
        }
    }

    private void handleErrorOperationsChangeQuantity(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    cartChangeQuantityLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            cartChangeQuantityLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    public void removeProduct(int product_id) {
        keyboardUtils.hideKeyboard();
        cartRemoveLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.removeProductFromMyCart(Constants.BEARER + preference.getTokenUser(), product_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultRemove, this::handleErrorRemove));
    }

    private void handleResultRemove(CartModel cartModel) {
        if (cartModel != null && cartModel.getSuccess()) {
            cartRemoveLiveData.setValue(Resource.success(cartModel));
        } else {
            cartRemoveLiveData.setValue(Resource.error("error", null));
        }
    }


    private void handleErrorRemove(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    cartRemoveLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            cartRemoveLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    public void chooseAddress(int addressId) {
        keyboardUtils.hideKeyboard();
        cartChooseAddressLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.chooseAddress(Constants.BEARER + preference.getTokenUser(), addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultAddress, this::handleErrorAddress));
    }

    private void handleErrorAddress(Throwable throwable) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) throwable).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) throwable).response().errorBody());
                    cartChooseAddressLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            cartChooseAddressLiveData.setValue(Resource.error(throwable.getMessage(), null));
        }
    }

    private void handleResultAddress(CartOperationsResponse cartOperationsResponse) {
        if (cartOperationsResponse != null && cartOperationsResponse.getmSuccess()){
            cartChooseAddressLiveData.setValue(Resource.success(cartOperationsResponse));
        }else {
            cartChooseAddressLiveData.setValue(Resource.error("Error" , null));
        }
    }

    @ViewModelInject
    public CartViewModel(@Assisted SavedStateHandle savedStateHandle
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

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }
}
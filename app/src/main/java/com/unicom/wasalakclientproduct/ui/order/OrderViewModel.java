package com.unicom.wasalakclientproduct.ui.order;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.order.OrderResponse;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;


@FragmentScoped
public class OrderViewModel extends ViewModel {
    //region Members
    private String TAG = this.getClass().getSimpleName();
    private KeyboardUtils keyboardUtils;
    private SavedStateHandle savedStateHandle;
    private UserRepository userRepository;
    private PreferenceUtils preference;
    private Retrofit retrofit;
    private CompositeDisposable composite;
    public MutableLiveData<Resource<OrderResponse>> _orderDetail;
    public MutableLiveData<Resource<OrderResponse>> _changeOrderStatus;

    //endregion

    //region Helper Functions

    @ViewModelInject
    public OrderViewModel(KeyboardUtils keyboardUtils, @Assisted SavedStateHandle savedStateHandle, UserRepository userRepository, PreferenceUtils preference, Retrofit retrofit) {
        this.keyboardUtils = keyboardUtils;
        this.savedStateHandle = savedStateHandle;
        this.userRepository = userRepository;
        this.preference = preference;
        this.retrofit = retrofit;
        composite = new CompositeDisposable();
    }

    public LiveData<Resource<OrderResponse>> ObserveOrderDetail(int id) {
        if (_orderDetail == null) {
            _orderDetail = new MutableLiveData<>();
            getOrderDetails(id);
        }

        return _orderDetail;
    }

    public LiveData<Resource<OrderResponse>> ObserveOrderChangeStatus(int orderId, String cancelReason, int courierAvailability) {
        if (_orderDetail == null) {
            _changeOrderStatus = new MutableLiveData<>();
            orderChangeStatus(orderId, cancelReason, courierAvailability);
        }

        return _changeOrderStatus;
    }


    private void getOrderDetails(int id) {
        keyboardUtils.hideKeyboard();
        _orderDetail.setValue(Resource.loading(null));
        composite.add(userRepository.getOrderDetails(Constants.BEARER + preference.getTokenUser(), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handelSuccessResult, this::handelErrorResult));

    }

    private void orderChangeStatus(int orderId, String cancelReason, int courierAvailability) {
        keyboardUtils.hideKeyboard();
        _changeOrderStatus.setValue(Resource.loading(null));
        composite.add(userRepository.orderChangeStatus(Constants.BEARER + preference.getTokenUser(), orderId, cancelReason, courierAvailability)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handelChangeOrderSuccessResult, this::handelChangeOrderErrorResult));

    }

    private void handelErrorResult(Throwable t) {
        try {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    _orderDetail.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            _orderDetail.setValue(Resource.error(e.getMessage(), null));
        }
    }

    private void handelSuccessResult(OrderResponse object) {

        if (object != null && object.getSuccess()) {
            _orderDetail.setValue(Resource.success(object));
        } else {
            _orderDetail.setValue(Resource.error(object.getError().getMessage(), null));
        }
    }

    private void handelChangeOrderErrorResult(Throwable t) {
        try {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    _changeOrderStatus.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            _changeOrderStatus.setValue(Resource.error(e.getMessage(), null));
        }
    }

    private void handelChangeOrderSuccessResult(OrderResponse object) {

        if (object != null && object.getSuccess()) {
            _changeOrderStatus.setValue(Resource.success(object));
        } else {
            _changeOrderStatus.setValue(Resource.error(object.getError().getMessage(), null));
        }
    }


    //endregion

    //region Events

    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }

    //endregion
}

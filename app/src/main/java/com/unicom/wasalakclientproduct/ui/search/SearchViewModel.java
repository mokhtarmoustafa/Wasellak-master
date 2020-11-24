package com.unicom.wasalakclientproduct.ui.search;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.category.ProductVendorResponse;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.search.Search;
import com.unicom.wasalakclientproduct.model.search.SearchResponse;
import com.unicom.wasalakclientproduct.model.store.StoreResponse;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.ui.vendor.Resource;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.SingleLiveData;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;


@FragmentScoped
public class SearchViewModel extends ViewModel {

    //region Members
    String TAG = this.getClass().getSimpleName();
    private Retrofit retrofit;
    private KeyboardUtils keyboardUtils;
    private PreferenceUtils preference;
    private UserRepository userRepository;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> productName = new MutableLiveData<>();
    public MutableLiveData<Integer> storeId = new MutableLiveData<>();
    public MutableLiveData<Integer> activityId = new MutableLiveData<>();
    public MutableLiveData<Integer> distance = new MutableLiveData<>();
    public MutableLiveData<String> latitude = new MutableLiveData<>();
    public MutableLiveData<String> longitude = new MutableLiveData<>();

    public MutableLiveData<Boolean> isWithDiscount = new MutableLiveData<>();
    private MutableLiveData<Resource<SearchResponse>> searchMutableLiveData;
    private CompositeDisposable composite = new CompositeDisposable();
    private MutableLiveData<Boolean> saveSearch;
    private MutableLiveData<ArrayList<Search>> savedList;
    private MutableLiveData<Boolean> clearSearch;
    private MutableLiveData<Resource<StoreResponse>> allStores;

    //endregion
    //region Events

    @ViewModelInject
    public SearchViewModel(KeyboardUtils keyboardUtils, UserRepository userRepository, PreferenceUtils preference, Retrofit retrofit) {
        this.retrofit = retrofit;
        this.preference = preference;
        this.keyboardUtils = keyboardUtils;
        this.userRepository = userRepository;
    }

    //endregion
    //region Helper Functions

    private void getSavedListData() {
//        keyboardUtils.hideKeyboard();

        ArrayList<Search> searchData = preference.getSearchData();
        if (searchData != null && searchData.size() > 0) {
            savedList.setValue(searchData);
        } else {
            savedList.setValue(null);
        }


    }

    public LiveData<Resource<StoreResponse>> observerAllStores() {
        if (allStores == null) {
            allStores = new MutableLiveData<>();
            getAllStores();
        }

        return allStores;

    }

    private void getAllStores() {

        keyboardUtils.hideKeyboard();
        allStores.setValue(Resource.loading(null));
        composite.add(userRepository.getAllStores(Constants.BEARER + preference.getTokenUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleStoreResults, this::handleStoreError));

    }

    public LiveData<Boolean> observeSaveToSearchListData(Search search) {
        if (saveSearch == null) {
            saveSearch = new MutableLiveData();
        }
        saveSearchData(search);
        return (LiveData) saveSearch;
    }

    private void saveSearchData(Search searchData) {
        ArrayList<Search> search = preference.getSearchData();

        if (search != null) {
            if (!search.contains(searchData)) {
                search.add(searchData);
                preference.saveSearchData(search);
            }
        } else {
            search = new ArrayList<>();
            search.add(searchData);
            preference.saveSearchData(search);
        }

        saveSearch.setValue(true);

    }

    public LiveData<ArrayList<Search>> ObserveSavedSearchListData() {
        if (savedList == null) {
            savedList = new MutableLiveData();
        }
        getSavedListData();

        return (LiveData) savedList;
    }

    public LiveData<Resource<SearchResponse>> observeSearchResultData() {
        if (savedList == null)
            savedList = new MutableLiveData();
        if (searchMutableLiveData == null) {
            searchMutableLiveData = new MutableLiveData<>();
            searchAllProducts(productName.getValue(), distance.getValue(), latitude.getValue(), longitude.getValue(), storeId.getValue(), activityId.getValue(), isWithDiscount.getValue());
        }
        return (LiveData) searchMutableLiveData;
    }

    public LiveData<Resource<SearchResponse>> ObserveFilterSearchResultData(int distance, int storeId, int activityId, Boolean isWithDiscount) {
        if (savedList == null)
            savedList = new MutableLiveData();
        if (searchMutableLiveData == null)
            searchMutableLiveData = new MutableLiveData<>();

        searchAllProductsByFilter(productName.getValue(), distance, latitude.getValue(), longitude.getValue(), storeId, activityId, isWithDiscount);

        return (LiveData) searchMutableLiveData;
    }


    public LiveData<Boolean> observeClearSearchListData() {
        if (clearSearch == null) {
            clearSearch = new MutableLiveData<>();
        }
        clearSearchData();
        return clearSearch;
    }

    private void clearSearchData() {
        preference.saveSearchData(new ArrayList<>());
        clearSearch.setValue(true);
    }


    void searchAllProducts(
            String productName,
            Integer distance,
            String latitude,
            String longitude,
            Integer storeId,
            Integer activityId,
            Boolean isWithDiscount

    ) {

        if (composite != null) {
            composite.clear();
        }
        keyboardUtils.hideKeyboard();
        searchMutableLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.getAllProductsBySearch(Constants.BEARER + preference.getTokenUser(), productName, distance, latitude, longitude, storeId, activityId, isWithDiscount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));


    }


    void searchAllProductsByFilter(
            String productName,
            Integer distance,
            String latitude,
            String longitude,
            Integer storeId,
            Integer activityId,
            Boolean isWithDiscount


    ) {

        if (composite != null) {
            composite.clear();
        }
        keyboardUtils.hideKeyboard();
        searchMutableLiveData.setValue(Resource.loading(null));
        composite.add(userRepository.getAllProductsBySearch(Constants.BEARER + preference.getTokenUser(), productName, distance, latitude, longitude, storeId, activityId, isWithDiscount)
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
                    searchMutableLiveData.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            searchMutableLiveData.setValue(Resource.error(t.getMessage().toString(), null));
        }

    }

    private void handleResults(SearchResponse object) {
        if (object != null) {
            searchMutableLiveData.setValue(Resource.success(object));
        } else {
            searchMutableLiveData.setValue(Resource.error("error", null));
        }

    }


    private void handleStoreResults(StoreResponse object) {
        if (object != null) {
            allStores.setValue(Resource.success(object));
        } else {
            allStores.setValue(Resource.error("error", null));
        }

    }

    private void handleStoreError(Throwable t) {
        try {
//            if (((HttpException) t).response().code() == 500) {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    allStores.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            }
        } catch (Exception e) {
            allStores.setValue(Resource.error(t.getMessage().toString(), null));
        }

    }


    //endregion


}

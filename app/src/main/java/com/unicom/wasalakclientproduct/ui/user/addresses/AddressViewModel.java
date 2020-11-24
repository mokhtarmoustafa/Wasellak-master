package com.unicom.wasalakclientproduct.ui.user.addresses;

import android.content.Context;
import android.location.Address;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.unicom.wasalakclientproduct.model.CityClass;
import com.unicom.wasalakclientproduct.model.CityModel;
import com.unicom.wasalakclientproduct.model.CountryClass;
import com.unicom.wasalakclientproduct.model.CountryModel;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.model.user.address.AddressModelResponse;
import com.unicom.wasalakclientproduct.model.user.address.AddressResponse;
import com.unicom.wasalakclientproduct.model.user.address.Result;
import com.unicom.wasalakclientproduct.model.user.address.area.AreaData;
import com.unicom.wasalakclientproduct.model.user.address.area.AreaResponse;
import com.unicom.wasalakclientproduct.repository.GuestRepository;
import com.unicom.wasalakclientproduct.repository.LocationRepository;
import com.unicom.wasalakclientproduct.repository.UserRepository;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.KeyboardUtils;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.utils.SingleLiveData;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;

@FragmentScoped
public class AddressViewModel extends ViewModel {
    //region Members

    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private Result addressCustomData = new Result();
    private KeyboardUtils keyboardUtils;
    private SavedStateHandle savedStateHandle;
    private UserRepository userRepository;
    private PreferenceUtils preference;
    private Retrofit retrofit;
    private CompositeDisposable composite = new CompositeDisposable();
    private MutableLiveData<Resource<AddressResponse>> addressResponse;
    private MutableLiveData<Resource<AddressModelResponse>> addressModelResponseLiveData = new MutableLiveData<>();
    public MutableLiveData<Result> addressData = new MutableLiveData<>();
    private Result result = new Result();
    public MutableLiveData<String> addressId = new MutableLiveData<>();
    public MutableLiveData<String> addressName = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<Integer> countryId = new MutableLiveData<>();
    public MutableLiveData<String> countryName = new MutableLiveData<>();
    public MutableLiveData<Integer> cityId = new MutableLiveData<>();
    public MutableLiveData<String> cityName = new MutableLiveData<>();
    public MutableLiveData<Integer> areaId = new MutableLiveData<>();
    public MutableLiveData<String> areaName = new MutableLiveData<>();
    public MutableLiveData<String> buildingId = new MutableLiveData<>();
    public MutableLiveData<String> flatId = new MutableLiveData<>();
    public MutableLiveData<String> floorId = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<String> latitude = new MutableLiveData<>();
    public MutableLiveData<String> mapData = new MutableLiveData<>();

    public MutableLiveData<String> longitude = new MutableLiveData<>();
    public MutableLiveData<Boolean> openMap = new MutableLiveData<>();

    private MutableLiveData<List<CountryClass>> countriesMutableLiveData;
    private MutableLiveData<List<CityClass>> citiesMutableLiveData;
    private MutableLiveData<List<AreaData>> areasMutableLiveData;
    public MutableLiveData<Boolean> enableButton = new MutableLiveData<>();
    private GuestRepository guestRepository;
    private LocationRepository locationRepository;
    private MutableLiveData<UserLocation> userLocationMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Address> addressSendMutableLiveData = new MutableLiveData<>();

    public LiveData<UserLocation> observeUserLocation() {
        return userLocationMutableLiveData;
    }

    public LiveData<Address> userCurrentLocationLiveDataObserver() {
        return addressSendMutableLiveData;
    }

    public LiveData<Resource<AddressModelResponse>> userAddressLiveDataObserver() {
        return addressModelResponseLiveData;
    }


    @ViewModelInject
    public AddressViewModel(@ActivityContext Context context, KeyboardUtils keyboardUtils,
                            @Assisted SavedStateHandle savedStateHandle, UserRepository userRepository,
                            PreferenceUtils preference, Retrofit retrofit,
                            LocationRepository locationRepository,
                            GuestRepository guestRepository) {
        this.context = context;
        this.keyboardUtils = keyboardUtils;
        this.savedStateHandle = savedStateHandle;
        this.userRepository = userRepository;
        this.preference = preference;
        this.retrofit = retrofit;
        this.guestRepository = guestRepository;
        this.locationRepository = locationRepository;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        composite.clear();
    }


    public LiveData<Result> getUserMutableLiveData() {

        if (addressData == null) {
            addressData = new MutableLiveData<>();
        }
        return addressData;
    }

    public void loadAddressData(Result addressData) {
        result = addressData;
        addressId.setValue(String.valueOf(addressData.getId()));
        addressName.setValue(addressData.getName());
        address.setValue(addressData.getDescription());
        countryId.setValue(addressData.getCountryId());
        countryName.setValue(addressData.getAreaCityCountryName());
        cityId.setValue(addressData.getCityId());
        cityName.setValue(addressData.getAreaCityName());
        areaId.setValue(addressData.getAreaId());
        areaName.setValue(addressData.getAreaName());
        mapData.setValue(addressData.getMapAddress());
        latitude.setValue(addressData.getLatitude());
        longitude.setValue(addressData.getLongitude());
        buildingId.setValue(addressData.getBuildingNumber());
        floorId.setValue(addressData.getFloor());
        flatId.setValue(addressData.getFlatNumber());

    }

    public LiveData<Resource<AddressResponse>> getMyAddressesLiveData() {
        if (addressResponse == null)
            addressResponse = new MutableLiveData<>();
        getMyAddresses(Constants.BEARER + preference.getTokenUser());
        return addressResponse;
    }

    public LiveData<Resource<AddressResponse>> getAddressByIdLiveData(int id) {
        if (addressResponse == null)
            addressResponse = new MutableLiveData<>();
        getAddressById(Constants.BEARER + preference.getTokenUser(), id);
        return addressResponse;
    }

    public LiveData<Resource<AddressResponse>> createNewAddressLiveData(Result data) {
        if (addressResponse == null)
            addressResponse = new MutableLiveData<>();
        createAddress(Constants.BEARER + preference.getTokenUser(), data);
        return addressResponse;
    }

    public LiveData<Resource<AddressResponse>> updateAddressLiveData(Result data) {
        if (addressResponse == null)
            addressResponse = new MutableLiveData<>();
        updateAddress(Constants.BEARER + preference.getTokenUser(), data);
        return addressResponse;
    }

    public LiveData<Resource<AddressResponse>> removeAddressLiveData(int id) {
        if (addressResponse == null)
            addressResponse = new MutableLiveData<>();
        deleteAddress(Constants.BEARER + preference.getTokenUser(), id);
        return addressResponse;
    }

    public LiveData<Boolean> getOpenMapMutableLiveData() {
        if (openMap == null) {
            openMap = new SingleLiveData<>();
        }
        return openMap;
    }

    //endregion
    //region Helper Functions


    private void getMyAddresses(String token) {
        keyboardUtils.hideKeyboard();
        addressResponse.setValue(Resource.loading(null));
        composite.add(userRepository.getMyAddresses(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError));

    }

    private void getAddressById(String token, int id) {
        keyboardUtils.hideKeyboard();
        addressResponse.setValue(Resource.loading(null));
        composite.add(userRepository.getAddressById(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAddressModelResults, this::handleError));

    }

    private void createAddress(String token, Result addressData) {
        keyboardUtils.hideKeyboard();
        addressResponse.setValue(Resource.loading(null));
        composite.add(userRepository.createAddress(token, addressData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAddressModelResults, this::handleError));

    }


    private void updateAddress(String token, Result addressData) {
        keyboardUtils.hideKeyboard();
        addressResponse.setValue(Resource.loading(null));
        composite.add(userRepository.updateAddress(token, addressData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAddressModelResults, this::handleError));

    }

    private void deleteAddress(String token, int id) {
        keyboardUtils.hideKeyboard();
        addressResponse.setValue(Resource.loading(null));
        composite.add(userRepository.deleteAddress(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleAddressModelResults, this::handleError));

    }


    private void handleError(Throwable t) {
        try {
            if (((HttpException) t).response().errorBody() != null) {
                Converter<ResponseBody, ForgetPasswordModel> errorConverter =
                        retrofit.responseBodyConverter(ForgetPasswordModel.class, new Annotation[0]);
                try {
                    ForgetPasswordModel error = errorConverter.convert(((HttpException) t).response().errorBody());
                    addressResponse.setValue(Resource.error(error.getError().getDetails().toString(), null));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            addressResponse.setValue(Resource.error(t.getMessage(), null));
        }
    }

    private void handleAddressModelResults(AddressModelResponse object) {

        if (object != null && object.getSuccess()) {
            addressModelResponseLiveData.setValue(Resource.success(object));
        } else {
            addressModelResponseLiveData.setValue(Resource.error(object.getError().getMessage(), null));
        }
    }


    private void handleResults(AddressResponse object) {

        if (object != null && object.getSuccess()) {
            addressResponse.setValue(Resource.success(object));
        } else {
            addressResponse.setValue(Resource.error(object.getError().getMessage(), null));
        }

    }


    public MutableLiveData<List<CountryClass>> getCountriesMutableLiveData() {
        if (countriesMutableLiveData == null) {
            countriesMutableLiveData = new MutableLiveData<>();
            retrieveCountries();
        }
        return countriesMutableLiveData;
    }

    public MutableLiveData<List<CityClass>> getCitiesMutableLiveData() {
        if (citiesMutableLiveData == null) {
            citiesMutableLiveData = new MutableLiveData<>();
        }
        return citiesMutableLiveData;
    }

    public MutableLiveData<List<AreaData>> getAreasMutableLiveData() {
        if (areasMutableLiveData == null) {
            areasMutableLiveData = new MutableLiveData<>();
        }
        return areasMutableLiveData;
    }

    private void retrieveCountries() {
        isLoading.setValue(true);
        composite.add(guestRepository.getCountries().map(new Function<CountryModel, List<CountryClass>>() {
            @Override
            public List<CountryClass> apply(CountryModel countryModel) throws Throwable {
                List<CountryClass> countries = new ArrayList<>();
                if (preference.getLang().equals("en")) {
                    for (int i = 0; i < countryModel.getResult().size(); i++) {
                        countries.add(new CountryClass(countryModel.getResult().get(i).getId(), countryModel.getResult().get(i).getDisplayName()));
                    }
                } else {
                    for (int i = 0; i < countryModel.getResult().size(); i++) {
                        countries.add(new CountryClass(countryModel.getResult().get(i).getId(), countryModel.getResult().get(i).getDisplayName()));
                    }
                }
                return countries;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResultsCountries, this::handleError));
    }

    private void handleResultsCountries(List<CountryClass> countries) {
        if (countries.size() > 0) {
            countriesMutableLiveData.setValue(countries);
        }
        isLoading.setValue(false);
    }

    public void retrieveCities() {
        isLoading.setValue(true);
        composite.add(guestRepository.getCities(countryId.getValue()).map(new Function<CityModel, List<CityClass>>() {
            @Override
            public List<CityClass> apply(CityModel cityModel) throws Throwable {
                List<CityClass> cities = new ArrayList<>();
                if (preference.getLang().equals("en")) {
                    for (int i = 0; i < cityModel.getResult().size(); i++) {
                        cities.add(new CityClass(cityModel.getResult().get(i).getId(), cityModel.getResult().get(i).getDisplayName()));
                    }
                } else {
                    for (int i = 0; i < cityModel.getResult().size(); i++) {
                        cities.add(new CityClass(cityModel.getResult().get(i).getId(), cityModel.getResult().get(i).getDisplayName()));
                    }
                }
                return cities;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResultsCities, this::handleError));
    }

    public void retrieveAreas() {
        isLoading.setValue(true);
        composite.add(guestRepository.getAreas(cityId.getValue()).map(new Function<AreaResponse, List<AreaData>>() {
            @Override
            public List<AreaData> apply(AreaResponse areaModel) throws Throwable {
                List<AreaData> areas = new ArrayList<>();
                if (preference.getLang().equals("en")) {
                    for (int i = 0; i < areaModel.getResult().size(); i++) {
                        areas.add(new AreaData(areaModel.getResult().get(i).getId(), areaModel.getResult().get(i).getDisplayName()));
                    }
                } else {
                    for (int i = 0; i < areaModel.getResult().size(); i++) {
                        areas.add(new AreaData(areaModel.getResult().get(i).getId(), areaModel.getResult().get(i).getDisplayName()));
                    }
                }
                return areas;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResultsAreas, this::handleError));
    }


    private void handleResultsCities(List<CityClass> cities) {
        citiesMutableLiveData.setValue(cities);
        isLoading.setValue(false);
    }

    private void handleResultsAreas(List<AreaData> areas) {
        areasMutableLiveData.setValue(areas);
        isLoading.setValue(false);
    }


    public void onClick(Boolean editMode) {
        keyboardUtils.hideKeyboard();
        enableButton.setValue(false);


        if (editMode) {
            addressCustomData = new Result(Integer.parseInt(addressId.getValue()), addressName.getValue(), address.getValue(),
                    buildingId.getValue(), floorId.getValue(),
                    flatId.getValue(), result.getMapAddress(), result.getmLatitude(), result.getmLongitude(), areaId.getValue());
        } else {
            addressCustomData = new Result(addressName.getValue(), address.getValue(),
                    buildingId.getValue(), floorId.getValue(),
                    flatId.getValue(), mapData.getValue(), latitude.getValue(), longitude.getValue(), areaId.getValue());
        }

        addressData.setValue(addressCustomData);


    }

    public void openMap() {
        keyboardUtils.hideKeyboard();
        openMap.setValue(true);
    }

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


    public void onConfirmLocation(Address address) {
        keyboardUtils.hideKeyboard();
        Constants.CURRENT_ADDRESS = address;
        addressSendMutableLiveData.setValue(address);

    }


    //endregion


}

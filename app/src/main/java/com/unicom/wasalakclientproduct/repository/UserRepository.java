package com.unicom.wasalakclientproduct.repository;

import com.unicom.wasalakclientproduct.model.CityModel;
import com.unicom.wasalakclientproduct.model.CountryModel;
import com.unicom.wasalakclientproduct.model.StructueMode;
import com.unicom.wasalakclientproduct.model.VendorModel;
import com.unicom.wasalakclientproduct.model.branch.BranchResponse;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.cart.ChangeQuantityCartDTO;
import com.unicom.wasalakclientproduct.model.cart.ConfirmOrderDTO;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.category.ProductVendorResponse;
import com.unicom.wasalakclientproduct.model.order.OrderResponse;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;
import com.unicom.wasalakclientproduct.model.search.SearchResponse;
import com.unicom.wasalakclientproduct.model.store.StoreResponse;
import com.unicom.wasalakclientproduct.model.user.AccountModel;
import com.unicom.wasalakclientproduct.model.user.ChangePassUser;
import com.unicom.wasalakclientproduct.model.user.LanguageClass;
import com.unicom.wasalakclientproduct.model.user.MarketPlaceTypeModel;
import com.unicom.wasalakclientproduct.model.user.UpdateProfileDTO;
import com.unicom.wasalakclientproduct.model.user.UpdateProfileModel;
import com.unicom.wasalakclientproduct.model.user.UploadImageModel;
import com.unicom.wasalakclientproduct.model.user.address.AddressModelResponse;
import com.unicom.wasalakclientproduct.model.user.address.AddressResponse;
import com.unicom.wasalakclientproduct.model.user.address.Result;
import com.unicom.wasalakclientproduct.remote.UserService;
import com.unicom.wasalakclientproduct.ui.orderCreate.OrderCreateModel;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;

@ActivityRetainedScoped
public class UserRepository {

    private UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<AccountModel> getAccount(String token) {
        return userService.account(token);
    }

    public Single<CountryModel> getCountries() {
        return userService.countriesRequest();
    }

    public Single<CityModel> getCities(String token, int countryId) {
        return userService.citiesRequest(token, countryId);
    }


    public Single<StructueMode> getLang(String token, LanguageClass languageClass) {
        return userService.language(token, languageClass);
    }

    public Single<UploadImageModel> getUpload(String token, MultipartBody.Part file) {
        return userService.uploadImage(token, file);
    }

    public Single<UpdateProfileModel> getUpdateProfile(String token, UpdateProfileDTO user) {
        return userService.updateUser(token, user);
    }


    public Single<StructueMode> getChangePass(String token, ChangePassUser changePassUser) {
        return userService.changePass(token, changePassUser);
    }


    public Single<BranchResponse> getBranchDetails(String token, Integer branchId, String latitude, String longitude) {
        return userService.getBranchDetails(token, branchId, latitude, longitude);
    }

    public Single<ProductResponse> getProductDetail(String token, int id) {
        return userService.getProductDetail(token, id);
    }


    public Single<VendorModel> getVendors(String token, int marketType, String lang, String lat, Integer distance) {
        return userService.getAllVendors(token, distance, lat, lang, marketType);
    }

    public Single<ProductVendorResponse> getAllProducts(String token, Boolean IsVendorDeleted, Integer CategoryId,
                                                        String Name,
                                                        String CreationTime,
                                                        Integer Status,
                                                        String Sorting,
                                                        Integer SkipCount,
                                                        Integer MaxResultCounte) {

        return userService.getAllProducts(token, IsVendorDeleted, CategoryId, Name, CreationTime, Status, Sorting, SkipCount, MaxResultCounte);
    }


    public Single<MarketPlaceTypeModel> getMarketPlaceType(String token, String name, String sorting, Integer skipCount, Integer maxCount) {
        return userService.marketPlaceTypeRequest(token, name, sorting, skipCount, maxCount);

    }

    public Single<AddressModelResponse> getAddressById(String token, int id) {
        return userService.getAddressById(token, id);

    }

    public Single<AddressModelResponse> createAddress(String token, Result addressData) {
        return userService.createAddress(token, addressData);

    }


    public Single<AddressModelResponse> updateAddress(String token, Result addressData) {
        return userService.updateAddress(token, addressData);

    }

    public Single<AddressModelResponse> deleteAddress(String token, int id) {
        return userService.deleteAddress(token, id);

    }

    public Single<AddressResponse> getMyAddresses(String token) {
        return userService.getMyAddresses(token);

    }

    public Single<AddressResponse> getAllAddresses(String token, String sorting, Integer skipCount, Integer maxResultCount) {
        return userService.getAllAddresses(token, sorting, skipCount, maxResultCount);
    }


    public Single<CartModel> getAddToCart(String token, AddToCartDTO addToCartDTO) {
        return userService.getAddToCart(token, addToCartDTO);
    }

    public Single<CartModel> removeProductFromMyCart(String token, int product_id) {
        return userService.removeProductFromMyCart(token, product_id);
    }

    public Single<CartModel> getMyCartDetail(String token) {
        return userService.getCartDetail(token);
    }

    public Single<CartModel> getDataToConfirmOrder(String token) {
        return userService.getDataToConfirmOrder(token);
    }

    public Single<CartModel> clearMyCartDetail(String token) {
        return userService.clearCartDetail(token);
    }

    public Single<CartModel> changeQuantity(String token, ChangeQuantityCartDTO changeQuantityCartDTO) {
        return userService.changeQuantity(token, changeQuantityCartDTO);
    }

    public Single<CartOperationsResponse> chooseAddress(String token, int addressId) {
        return userService.chooseAddress(token, addressId);
    }

    public Single<OrderCreateModel> confirmOrder(String token, ConfirmOrderDTO confirmOrderDTO) {
        return userService.confirmOrder(token, confirmOrderDTO);
    }

    public Single<OrderResponse> getOrderDetails(String token, int id) {
        return userService.getOrderDetails(token, id);
    }


    public Single<OrderResponse> orderChangeStatus(String token, int orderId, String cancelReason, int courierAvailability) {
        return userService.orderChangeStatus(token, orderId, cancelReason, courierAvailability);
    }


    public Single<SearchResponse> getAllProductsBySearch(String token,
                                                         String productName,
                                                         Integer distance,
                                                         String latitude,
                                                         String longitude,
                                                         Integer storeId,
                                                         Integer activityId,
                                                         Boolean isWithDiscount) {

        return userService.getAllProductsBySearch(token, productName, distance, latitude, longitude, storeId, activityId, isWithDiscount);
    }


    public Single<StoreResponse> getAllStores(String token) {
        return userService.getAllStores(token);
    }
}




package com.unicom.wasalakclientproduct.remote;

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
import com.unicom.wasalakclientproduct.ui.orderCreate.OrderCreateModel;

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UserService {
    @POST("services/app/User/ViewProfileForEdit")
    Single<AccountModel> account(@Header("Authorization") String token);

    @GET("services/app/Country/GetAllForDropDownList")
    Single<CountryModel> countriesRequest();

    @GET("services/app/City/GetAllForDropDownListWithCountryId")
    Single<CityModel> citiesRequest(@Header("Authorization") String token, @Query("CountryId") int countryId);

    @POST("services/app/User/ChangeLanguage")
    Single<StructueMode> language(@Header("Authorization") String token, @Body LanguageClass languageClass);

    @POST("services/app/User/ChangePassword")
    Single<StructueMode> changePass(@Header("Authorization") String token, @Body ChangePassUser changePassUser);

    @Multipart
    @POST("UploadFile/Upload")
    Single<UploadImageModel> uploadImage(@Header("Authorization") String token, @Part MultipartBody.Part file);

    @PUT("services/app/User/Update")
    Single<UpdateProfileModel> updateUser(@Header("Authorization") String token, @Body UpdateProfileDTO user);


    @POST("services/app/Branch/ViewBranchDetails")
    Single<BranchResponse> getBranchDetails(@Header("Authorization") String token,
                                            @Query("id") Integer branchId,
                                            @Query("Latitude") String latitude,
                                            @Query("Longitude") String longitude);

    @GET("services/app/Product/Get")
    Single<ProductResponse> getProductDetail(@Header("Authorization") String token, @Query("id") int id);


    @GET("services/app/Branch/GetAllBranches")
    Single<VendorModel> getAllVendors(@Header("Authorization") String token,
                                      @Query("Distance") Integer distance,
                                      @Query("Latitude") String latitude,
                                      @Query("Longitude") String longitude,
                                      @Query("activityId") int MarketPlaceTypeId
    );


    @GET("services/app/Product/GetAll")
    Single<ProductVendorResponse> getAllProducts(
            @Header("Authorization") String token,
            @Query("IsVendorDeleted") Boolean IsVendorDeleted,
            @Query("CategoryId") Integer CategoryId,
            @Query("Name") String Name,
            @Query("CreationTime") String CreationTime,
            @Query("Status") Integer Status,
            @Query("Sorting") String Sorting,
            @Query("SkipCount") Integer SkipCount,
            @Query("MaxResultCount") Integer MaxResultCount
    );

    @GET("services/app/Store/GetAllForDropDownList")
    Single<StoreResponse> getAllStores(@Header("Authorization") String token);


    @GET("services/app/Activity/GetAll")
    Single<MarketPlaceTypeModel> marketPlaceTypeRequest(@Header("Authorization") String token,
                                                        @Query("Name") String name,
                                                        @Query("Sorting") String sorting,
                                                        @Query("SkipCount") Integer skipCount,
                                                        @Query("MaxResultCount ") Integer MaxResultCount);


    @GET("services/app/ClientAddresses/Get")
    Single<AddressModelResponse> getAddressById(@Header("Authorization") String token, @Query("id") int id);

    @POST("services/app/ClientAddresses/Create")
    Single<AddressModelResponse> createAddress(@Header("Authorization") String token, @Body Result addressData);


    @DELETE("services/app/ClientAddresses/Delete")
    Single<AddressModelResponse> deleteAddress(@Header("Authorization") String token, @Query("id") int id);


    @PUT("services/app/ClientAddresses/Update")
    Single<AddressModelResponse> updateAddress(@Header("Authorization") String token, @Body Result addressData);

    @GET("services/app/ClientAddresses/GetMyAddresses")
    Single<AddressResponse> getMyAddresses(@Header("Authorization") String token);

    @GET("services/app/ClientAddresses/GetAll")
    Single<AddressResponse> getAllAddresses(@Header("Authorization") String token,
                                            @Query("Sorting") String Sorting,
                                            @Query("SkipCount") Integer SkipCount,
                                            @Query("MaxResultCount") Integer MaxResultCount);

    @GET("services/app/Product/GetAllClientSearchResults")
    Single<SearchResponse> getAllProductsBySearch(@Header("Authorization") String token,
                                                  @Query("ProductName") String productName,
                                                  @Query("Distance") Integer distance,
                                                  @Query("Latitude") String latitude,
                                                  @Query("Longitude") String longitude,
                                                  @Query("StoreId") Integer storeId,
                                                  @Query("ActivityId") Integer activityId,
                                                  @Query("IsWithDiscount") Boolean isWithDiscount);


    @POST("services/app/CartDetails/AddProductToMyCart")
    Single<CartModel> getAddToCart(@Header("Authorization") String token,
                                   @Body AddToCartDTO addToCartDTO);

    @DELETE("services/app/CartDetails/RemoveProductFromMyCart")
    Single<CartModel> removeProductFromMyCart(@Header("Authorization") String token,
                                              @Query("productId") int product_id);

    @GET("services/app/Cart/GetMyCartDetails")
    Single<CartModel> getCartDetail(@Header("Authorization") String token);

    @GET("services/app/Cart/GetDataToConfirmOrder")
    Single<CartModel> getDataToConfirmOrder(@Header("Authorization") String token);

    @POST("services/app/CartDetails/EmptyMyCart")
    Single<CartModel> clearCartDetail(@Header("Authorization") String token);

    @POST("services/app/Cart/ChangeQuantity")
    Single<CartModel> changeQuantity(@Header("Authorization") String token, @Body ChangeQuantityCartDTO changeQuantityCartDTO);

    @POST("services/app/Cart/ChangeDeliveryAddress")
    Single<CartOperationsResponse> chooseAddress(@Header("Authorization") String token, @Query("deliveryAddressId") int addressId);

    @POST("services/app/Order/Create")
    Single<OrderCreateModel> confirmOrder(@Header("Authorization") String token, @Body ConfirmOrderDTO confirmOrderDTO);

    @GET("services/app/Order/Get")
    Single<OrderResponse> getOrderDetails(@Header("Authorization") String token, @Query("id") int id);

    @POST("services/app/Order/ChangeStatus")
    Single<OrderResponse> orderChangeStatus(@Header("Authorization") String token,
                                            @Query("orderId") int orderId,
                                            @Query("cancelReason") String cancelReason,
                                            @Query("courierAvailability") int courierAvailability);

}

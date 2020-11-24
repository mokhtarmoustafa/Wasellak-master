package com.unicom.wasalakclientproduct.remote;

import com.unicom.wasalakclientproduct.model.CityModel;
import com.unicom.wasalakclientproduct.model.CountryModel;
import com.unicom.wasalakclientproduct.model.guest.ForgetPassUSer;
import com.unicom.wasalakclientproduct.model.guest.ForgetPasswordModel;
import com.unicom.wasalakclientproduct.model.guest.LoginModel;
import com.unicom.wasalakclientproduct.model.guest.LoginUser;
import com.unicom.wasalakclientproduct.model.guest.RegisterModel;
import com.unicom.wasalakclientproduct.model.guest.RegisterUser;
import com.unicom.wasalakclientproduct.model.guest.ResetPasswordUser;
import com.unicom.wasalakclientproduct.model.productDetails.Area;
import com.unicom.wasalakclientproduct.model.user.address.area.AreaResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GuestService {
    @POST("TokenAuth/Authenticate")
    Single<LoginModel> loginRequest(@Body LoginUser user);

    @POST("TokenAuth/MobileExternalAuthenticate")
    Single<LoginModel> loginExternalRequest(@Query("UserId")String userId, @Query("ProfilePictureUrl")String profilePictureUrl, @Query("UserName")String userName, @Query("FullName")String fullName, @Query("Email")String email, @Query("pageLink")String pageLink );

    @POST("services/app/Account/Register")
    Single<RegisterModel> registerRequest(@Body RegisterUser user);

    @POST("services/app/Account/ForgotPassword")
    Single<ForgetPasswordModel> forgetRequest(@Body ForgetPassUSer user);

    @POST("services/app/User/ResetPassword")
    Single<ForgetPasswordModel> resetPassword(@Body ResetPasswordUser user);

    @GET("services/app/Country/GetAllForDropDownList")
    Single<CountryModel> countriesRequest();

    @GET("services/app/City/GetAllForDropDownListWithCountryId")
    Single<CityModel> citiesRequest(@Query("CountryId") int countryId);

    @GET("services/app/Area/GetAllForDropDownListWithCityId")
    Single<AreaResponse> areasRequest(@Query("CityId") int cityId);


}

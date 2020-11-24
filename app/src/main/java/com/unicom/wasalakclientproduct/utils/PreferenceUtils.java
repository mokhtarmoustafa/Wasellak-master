package com.unicom.wasalakclientproduct.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unicom.wasalakclientproduct.model.guest.RegisterModel;
import com.unicom.wasalakclientproduct.model.productDetails.User;
import com.unicom.wasalakclientproduct.model.search.Search;
import com.unicom.wasalakclientproduct.model.user.AccountModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceUtils {
    SharedPreferences preference;

    @Inject
    public PreferenceUtils(SharedPreferences preference) {
        this.preference = preference;
    }

    public boolean saveTokenDevice(String deviceToken) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("device_token", deviceToken);
        editor.apply();
        return true;
    }

    public String getTokenDevice() {
        return preference.getString("device_token", "");
    }


    public boolean saveTokenUser(String userToken) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("user_token", userToken);
        editor.apply();
        return true;
    }

    public String getTokenUser() {
        return preference.getString("user_token", "");
    }

    public boolean saveNotificationState(Boolean notificationState) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("notification_state", notificationState);
        editor.apply();
        return true;
    }

    public Boolean getNotification() {
        return preference.getBoolean("notification_state", true);
    }

    public String getLang() {
        return preference.getString("language", "ar");
    }

//    public Boolean saveLang(String language)
//    {
//        SharedPreferences.Editor editor = preference.edit();
//        editor.putString("language", language);
//        editor.apply();
//        return true;
//    }


    public Boolean setUserImage(String userImage) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("userImage", userImage);
        editor.apply();
        return true;
    }

    public String getUserImage() {
        return preference.getString("userImage", "");
    }

    public Boolean setCartCount(int cart_count) {
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt("cart_count", cart_count);
        editor.apply();
        return true;
    }
    public Integer getCartCount() {
        return preference.getInt("cart_count", 0);
    }


    public void saveUserdata(AccountModel user) {
        SharedPreferences.Editor editor = preference.edit();
        Gson gson = new Gson();
        if (user == null) {
            return;
        }
        String json = gson.toJson(user);
        editor.putString("user_data", json);
        editor.apply();
    }

    public AccountModel getUserData() {
        Gson gson = new Gson();
        String json = preference.getString("user_data", null);
        return gson.fromJson(json, AccountModel.class);
    }

    public void saveSearchData(ArrayList<Search> searchData) {
        SharedPreferences.Editor editor = preference.edit();
        Gson gson = new Gson();
        if (searchData == null) {
            return;
        }
        String json = gson.toJson(searchData);
        editor.putString("search_data", json);
        editor.apply();
    }

    public ArrayList<Search> getSearchData() {
        Gson gson = new Gson();
        String json = preference.getString("search_data", null);
        Type type = new TypeToken<ArrayList<Search>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void clear() {
        preference.edit().clear().apply();
    }

}

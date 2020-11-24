package com.unicom.wasalakclientproduct.utils;

import android.location.Address;

import com.unicom.wasalakclientproduct.model.branch.Result;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;

public class Constants {
    public static final String ARABIC = "ar";
    public static final String ENGLISH = "en";
    public static final String BASE_IAMGE_URL="http://eg-unicom.dyndns.org:4100/api";

    public static final String KEY_USERLOGIN = "user_login";
    public static final String KEY_USERPASSWORD = "user_state";
    public static final String KEY_LOCALE = "language";
    public static final String TOKEN = "user_token";
    public static final String USERNAME = "user_name";
    public static final String USEREMAIL = "user_email";
    public static final String CURRENCY = "currency";

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static Result RESULT_DATA;
    public static ProductResponse PRODUCT_RESPONSE;
    public static Address CURRENT_ADDRESS;
    public static String BEARER="Bearer ";
}

package com.unicom.wasalakclientproduct.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unicom.wasalakclientproduct.reciever.NetworkReceiver;
import com.unicom.wasalakclientproduct.utils.ChangeLang;
import com.unicom.wasalakclientproduct.utils.GPSChecker;
import com.unicom.wasalakclientproduct.utils.IGPSCallBack;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity /*implements IGPSCallBack*/ {
    @Inject
    NetworkReceiver networkreceiver;
//    @Inject
//    GPSChecker GPSChecker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        registerReceiver(new GPSChecker(new GPSChecker.LocationCallBack() {
            @Override
            public void turnedOn() {
                Log.e("GPSChecker","is turned on");
            }
            @Override
            public void turnedOff() {
                Log.e("GPSChecker","is turned off");
            }
        }), new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ChangeLang.setLocale(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkreceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//        registerReceiver(GPSChecker, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkreceiver);
    }


//    @Override
//    public void turnedOn() {
//        Toasty.info(this, "turnedOn");
//    }
//
//    @Override
//    public void turnedOff() {
//        Toasty.info(this, "turnedOff");
//    }
}

package com.unicom.wasalakclientproduct.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static android.widget.Toast.*;

@AndroidEntryPoint
public class GPSChecker extends BroadcastReceiver  implements LocationListener {


    public interface LocationCallBack {
        void turnedOn();
        void turnedOff();
    }
    private  LocationCallBack locationCallBack;


    public GPSChecker(){
        if(locationCallBack==null)
        {
            locationCallBack=new LocationCallBack() {
                @Override
                public void turnedOn() {

                }

                @Override
                public void turnedOff() {

                }
            };
        }

    }

    public GPSChecker(LocationCallBack iLocationCallBack){
        this.locationCallBack = iLocationCallBack;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            locationCallBack.turnedOn();
        else
            locationCallBack.turnedOff();
    }


}
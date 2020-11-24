package com.unicom.wasalakclientproduct.utils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

class LocationLiveData extends   MutableLiveData<Location> {
    private static LocationRequest locationRequest;
    private  Context context;


    public static final long FASTEST_INTERVAL=500L;

    public  static final long INTERVAL=1000L;
public  LocationLiveData(Context context){
    this.context=context;
}

    private FusedLocationProviderClient mFusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(context);




    private static void  setupLoctiob(){
         locationRequest = LocationRequest.create();
        locationRequest.setInterval(INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }



    /**
     * Called when the number of active observers change to 1 from 0.
     * This callback can be used to know that this LiveData is being used thus should be kept
     * up to date.
     */
    @Override
    public void  onInactive() {
        super.onInactive();
        mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }


    /**
     * Called when the number of active observers change from 1 to 0.
     *
     * This does not mean that there are no observers left, there may still be observers but their
     * lifecycle states aren't STARTED or RESUMED
     * (like an Activity in the back stack).
     *
     * You can check if there are observers via hasObservers() method
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onActive() {
        super.onActive();
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null)
               setValue(location);
            }
        });

        startLocationUpdates();
    }

    /**
     * Callback that triggers on location updates available
     */
    private  LocationCallback locationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                setValue(location);
            }
        }
    };


    /**
     * Initiate Location Updates using Fused Location Provider and
     * attaching callback to listen location updates
     */
    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mFusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        );
    }

}
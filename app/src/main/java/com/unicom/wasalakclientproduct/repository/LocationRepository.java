package com.unicom.wasalakclientproduct.repository;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.unicom.wasalakclientproduct.model.user.UserLocation;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class LocationRepository  {

    private  Geocoder geocoder;
    private FusedLocationProviderClient mFusedLocationClient;

    @Inject
    public  LocationRepository(Geocoder geocoder, FusedLocationProviderClient fusedLocationProviderClient){
        this.geocoder=geocoder;
        this.mFusedLocationClient=fusedLocationProviderClient;
    }



    @SuppressLint("MissingPermission")
    public Single<UserLocation> getLastLocation() {

        return Single.create(e -> {
            mFusedLocationClient.
                    getLastLocation().
                    addOnSuccessListener(location -> {
                        if (location != null) {
                            try {
                                List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                String cityName = address.get(0).getLocality();
                                Address currentAddress=address.get(0);
                                UserLocation userLocation=new UserLocation();
                                userLocation.setCurrentAddress(currentAddress);
                                userLocation.setLatitude(location.getLatitude());
                                userLocation.setLongitude(location.getLongitude());
                                userLocation.setAddress(cityName);
                                e.onSuccess(userLocation);


                            } catch (IOException e1) {
                                e.onError(e1);
                            }

                        } else
                            e.onError(new Throwable("Your location settings is turned off"));
                    });

        });
    }
}

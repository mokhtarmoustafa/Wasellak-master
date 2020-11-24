package com.unicom.wasalakclientproduct.model.user;

import android.location.Address;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class UserLocation {
    double longitude;
    double latitude;
    String address;
    Address currentAddress;

    @Inject
    public UserLocation() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String prepareAddressData(Address currentAddress) {
        Log.d("prepareAddressData", "Address Line: "+currentAddress);
        StringBuilder builder = new StringBuilder();
        if(currentAddress.getFeatureName()!=null&&!currentAddress.getFeatureName().isEmpty())
        builder.append(currentAddress.getFeatureName()).append(" , ");
        if(currentAddress.getSubAdminArea()!=null&&!currentAddress.getSubAdminArea().isEmpty())
            builder.append(currentAddress.getSubAdminArea()).append(" , ");
        if(currentAddress.getAdminArea()!=null&&!currentAddress.getAdminArea().isEmpty())
        builder.append(currentAddress.getAdminArea()).append(" , ");
        if(currentAddress.getCountryName()!=null&&!currentAddress.getCountryName().isEmpty())
        builder.append(currentAddress.getCountryName());
        Log.d("prepareAddressData", "prepareAddressData: "+builder.toString());
        return builder.toString();
    }
}

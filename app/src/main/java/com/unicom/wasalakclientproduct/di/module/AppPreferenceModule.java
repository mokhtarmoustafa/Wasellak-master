package com.unicom.wasalakclientproduct.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;

import androidx.preference.PreferenceManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.unicom.wasalakclientproduct.utils.GPSChecker;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@InstallIn(ApplicationComponent.class)
@Module
public class AppPreferenceModule {

    @Provides
    @Singleton
    public SharedPreferences providePreference(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    @Singleton
    public FusedLocationProviderClient provideFusedClient(@ApplicationContext Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }


    @Provides
    @Singleton
    public Geocoder provideGeocoder(@ApplicationContext Context context) {
        return new Geocoder(context, Locale.getDefault());
    }





}

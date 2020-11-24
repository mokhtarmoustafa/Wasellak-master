package com.unicom.wasalakclientproduct.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.ActivityUserBinding;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.ui.BaseActivity;
import com.unicom.wasalakclientproduct.utils.AppPermissions;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.viewmodel.user.UserViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserActivity extends BaseActivity {
    public ActivityUserBinding binding;
    public static int id = -1;
    @Inject
    AppPermissions appPermissions;
    @Inject
    PreferenceUtils preference;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private LocationCallback locationCallback;
    public BadgeDrawable badgeDrawable;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (appPermissions.checkAndRequestPermission()) {
            setupLocation();
        }

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupBadge();
        setUpNavigation();

    }

    private void setupLocation() {
        //check if gps is enabled or not and then request user to enable it
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());
        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(UserActivity.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        mFusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(Task<Location> task) {
                        if (task.isSuccessful()) {
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {

//                                requestsViewModel.lat.setValue(String.valueOf(mLastKnownLocation.getLatitude()));
//                                requestsViewModel.lng.setValue(String.valueOf(mLastKnownLocation.getLongitude()));
//                                if (activityClick != null) {
//                                    requestsViewModel.onclick(activityClick);
//                                }
                            } else {
                                final LocationRequest locationRequest = LocationRequest.create();
                                locationRequest.setInterval(10000);
                                locationRequest.setFastestInterval(5000);
                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult) {
                                        super.onLocationResult(locationResult);
                                        if (locationResult == null) {
                                            return;
                                        }
                                        mLastKnownLocation = locationResult.getLastLocation();
                                        if (mLastKnownLocation != null) {
//                                            requestsViewModel.lat.setValue(String.valueOf(mLastKnownLocation.getLatitude()));
//                                            requestsViewModel.lng.setValue(String.valueOf(mLastKnownLocation.getLongitude()));
//                                            if (activityClick != null) {
//                                                requestsViewModel.onclick(activityClick);
//                                            }
                                        }
                                        mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
                                    }
                                };
                                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        } else {
                            //unable_to_get_last_location
                        }
                    }
                });
    }

    public void setupBadge() {
        badgeDrawable = binding.bottomNavigation.getOrCreateBadge(R.id.cartFragment);
        badgeDrawable.setBackgroundColor(Color.parseColor("#00CFF7"));
        badgeDrawable.setBadgeTextColor(Color.BLACK);
        badgeDrawable.setMaxCharacterCount(3);
        badgeDrawable.setVisible(false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if (getIntent().getBooleanExtra("fromLogin" , false)) {
            getCountCart();
        }else {
            if (preference.getCartCount() > 0) {
                badgeDrawable.setNumber(preference.getCartCount());
                badgeDrawable.setVisible(true);
            }
        }
    }

    public void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.user_nav);

        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt("id");
            if (id == 0) {
                NavigationUI.setupWithNavController(binding.bottomNavigation,
                        navHostFragment.getNavController());
                return;
            }
            NavInflater navInflater = navHostFragment.getNavController().getNavInflater();
            NavGraph navGraph = navInflater.inflate(R.navigation.user_nav_graph);
            NavController navController = navHostFragment.getNavController();
            navGraph.setStartDestination(R.id.notificationFragment);
            navController.setGraph(navGraph);
            NavigationUI.setupWithNavController(binding.bottomNavigation,
                    navController);
        } else {
            NavigationUI.setupWithNavController(binding.bottomNavigation,
                    navHostFragment.getNavController());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (appPermissions.onRequestPermissionsResult(requestCode, grantResults, permissions)) {
            setupLocation();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 51 && resultCode == 0) {
            this.finishAffinity();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        Navigation.findNavController(this, R.id.user_nav).navigateUp();
        return true;
    }

    public void getCountCart() {
        viewModel.observeListProductToCart().observe(this, new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartModelResource) {
                switch (cartModelResource.status) {
                    case SUCCESS:
                        int i = cartModelResource.data.getResult().getTotalProduct();
                        if (i > 0) {
                            badgeDrawable.setNumber(i);
                            badgeDrawable.setVisible(true);
                            preference.setCartCount(i);
                        } else {
                            badgeDrawable.setVisible(false);
                        }
                        break;

                }
            }
        });
    }
}
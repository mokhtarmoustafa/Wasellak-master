package com.unicom.wasalakclientproduct.ui.user.addresses;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arsy.maps_library.MapRipple;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentLocationAddressBinding;
import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.utils.SingleLiveData;
import com.unicom.wasalakclientproduct.utils.StringUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationAddressFragment extends Fragment implements OnMapReadyCallback {

    //region Members
    String TAG = this.getClass().getSimpleName();
    private NavController navController;
    private GoogleMap googleMap;
    private FragmentLocationAddressBinding binding;
    private MutableLiveData<Address> currentAddress = new MutableLiveData();
    private AddressViewModel viewModel;
    private UserLocation currentUserLocation;
    MapRipple mapRipple;
    Float DEFAULT_ZOOM = 18f;

    public LiveData<Address> observeUserLocation() {
        return currentAddress;
    }


    //endregion

//region Events


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location_address, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap();
        binding.setLifecycleOwner(this);
        binding.setFragment(this);


        if (navController == null) {
            navController = Navigation.findNavController(view);
        }


        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        observeUserLocation().observe(getViewLifecycleOwner(), new Observer<Address>() {
            @Override
            public void onChanged(Address address) {
                String addressData = getAddressFromLocation(new LatLng(address.getLatitude(), address.getLongitude()));
                binding.etAddress.setText(addressData);
                binding.setAddress(address);
            }
        });


        viewModel.observeUserLocation().observe(getViewLifecycleOwner(), new Observer<UserLocation>() {
            @Override
            public void onChanged(UserLocation userLocation) {
                currentUserLocation = userLocation;
                if (googleMap != null && userLocation != null) {
//                    mapRipple = new MapRipple(googleMap, new LatLng(userLocation.getLatitude(), userLocation.getLongitude()), requireContext());
//                    mapRipple.startRippleMapAnimation();
//                    rippleMap(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()));
                    moveCamera(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()));
                    String addressData = userLocation.prepareAddressData(userLocation.getCurrentAddress());//userLocation.getCurrentAddress().getAddressLine(0);
                    binding.etAddress.setText(addressData);

                }

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        this.googleMap = googleMap;


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            viewModel.requestUserCurrentLocation();

        } else return;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        googleMap.setMyLocationEnabled(false);

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng latlng = googleMap.getCameraPosition().target;

                if (latlng != null) {
                    Address address = getAddressFromLocation(latlng.latitude, latlng.longitude);
                    if (address != null) {
                        binding.setAddress(address);
                        currentAddress.setValue(address);

                    }

                }

            }
        });

        if (currentUserLocation != null) {
            LatLng location = new LatLng(currentUserLocation.getLatitude(), currentUserLocation.getLongitude());
            drawMarker(location);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    location, 15);
            googleMap.animateCamera(cameraUpdate);
        }

    }


    @Override
    public void onStop() {
        super.onStop();
//        if (mapRipple != null && mapRipple.isAnimationRunning()) {
//            mapRipple.stopRippleMapAnimation();
//        }
    }

    //endregion

//region Helper Functions

    private void initMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private Address getAddressFromLocation(
            Double latitude, Double longitude
    ) {

        Geocoder geocoder;
        List<Address> addresses;
        Address address = null;
        geocoder = new Geocoder(requireContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses.size() > 0)
                address = addresses.get(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

        } catch (IOException e) {
            e.printStackTrace();
        }


        return address;
    }

    private String getAddressFromLocation(
            LatLng latLng
    ) {
        String addressData = "";
        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            Address address = addresses.get(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            addressData = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressData;
    }


    public void drawMarker(LatLng latLng) {


        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_marker))
                .title("title"));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void moveCamera(LatLng latlng) {
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(latlng.latitude, latlng.longitude),
                        DEFAULT_ZOOM
                )
        );
    }

    private void rippleMap(LatLng latlng) {
        mapRipple = new MapRipple(googleMap, latlng, requireContext());
        mapRipple.withNumberOfRipples(3);
        mapRipple.withFillColor(Color.GREEN);
        mapRipple.withStrokeColor(Color.BLACK);
        mapRipple.withStrokewidth(10);      // 10dp
        mapRipple.withDistance(2000);      // 2000 metres radius
        mapRipple.withRippleDuration(12000);    //12000ms
        mapRipple.withTransparency(0.5f);
        mapRipple.startRippleMapAnimation();
    }

    public void sendAddressData(Address address) {
        if (address != null) {
            viewModel.onConfirmLocation(address);
            navController.navigateUp();
        }

    }
    //endregion


}
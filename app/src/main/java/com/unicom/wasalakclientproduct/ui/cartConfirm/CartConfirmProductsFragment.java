package com.unicom.wasalakclientproduct.ui.cartConfirm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import com.unicom.wasalakclientproduct.databinding.FragmentCartConfirmProductsBinding;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.ui.cart.CartViewModel;
import com.unicom.wasalakclientproduct.ui.orderCreate.OrderCreateModel;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class CartConfirmProductsFragment extends Fragment implements OnMapReadyCallback {
    @Inject
    CartProductsAdapter adapter;
    private NavController navController;
    private FragmentCartConfirmProductsBinding binding;
    private CartConfirmProductsViewModel viewModel;
    @Inject
    public PreferenceUtils preferenceUtils;
    private GoogleMap googleMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_confirm_products, container, false);
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }
        viewModel = new ViewModelProvider(this).get(CartConfirmProductsViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCartViewModel(viewModel);
        binding.setAdapter(adapter);
        binding.setFragment(this);
        initializePage();
        initMap();
    }

    private void initializePage(){
        if (preferenceUtils.getUserData() != null && preferenceUtils.getUserData().getResult() != null)
            binding.setAccount(preferenceUtils.getUserData().getResult());
        viewModel.observeListProductToCart().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartModelResource) {
                switch (cartModelResource.status) {
                    case ERROR:
                        ((UserActivity) getActivity()).badgeDrawable.setVisible(false);
                        break;
                    case SUCCESS:
                        drawMarker(Double.parseDouble(cartModelResource.data.getResult().getAddress().getLatitude()) , Double.parseDouble(cartModelResource.data.getResult().getAddress().getLongitude()));
                        adapter.submitList(cartModelResource.data.getResult().getCartsDetails());
                        break;
                }
            }
        });
    }

    private void initMap() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        this.googleMap = googleMap;

    }

    public void drawMarker(double lat , double lng) {
        LatLng location = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                location, 15);
        googleMap.moveCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions()
                .position(location)
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_baseline_location))
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

    public void onChangeAddress(){
        navController.navigateUp();
    }

    public void onClickConfirmOrder(){
        viewModel.observeCreateOrder().observe(getViewLifecycleOwner(), new Observer<Resource<OrderCreateModel>>() {
            @Override
            public void onChanged(Resource<OrderCreateModel> cartOperationsResponseResource) {
                switch (cartOperationsResponseResource.status) {
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        Toasty.error(getContext(), cartOperationsResponseResource.message, Toasty.LENGTH_LONG).show();
                        break;
                    case SUCCESS:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        CartConfirmProductsFragmentDirections.ActionCartConfirmProductsFragmentToCartSuccessFragment action= CartConfirmProductsFragmentDirections.actionCartConfirmProductsFragmentToCartSuccessFragment(cartOperationsResponseResource.data.getResult().getId());
                        navController.navigate(action);
                        break;
                }
            }
        });
        viewModel.createOrder();
    }
}
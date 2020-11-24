package com.unicom.wasalakclientproduct.ui.vendor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.slider.Slider;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentVendorFinderFrgamentBinding;
import com.unicom.wasalakclientproduct.model.VendorModel;
import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.utils.DrawableTextView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VendorFinderFrgament extends Fragment implements FilterVendorSheetDailog.OnFilterListener, VendorListAdapter.ClickListener {
    private String TAG = this.getClass().getSimpleName();
    private FragmentVendorFinderFrgamentBinding binding;
    private VendorListAdapter adapter;
    private VendorFinderViewModel vendorFinderViewModel;
    private UserLocation currentUserLocation;
    private int currentDistance = 0;
    private int market_type_id;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vendor_finder_frgament, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }
        binding.topAppBar.actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        binding.topAppBar.etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });
        binding.topAppBar.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });

        vendorFinderViewModel = new ViewModelProvider(this).get(VendorFinderViewModel.class);

        if (getArguments() != null) {
            market_type_id = VendorFinderFrgamentArgs.fromBundle(getArguments()).getMarketTypeId();
        }

        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(vendorFinderViewModel);
        setupRecycleView();
        subscribeObservers();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            vendorFinderViewModel.requestUserCurrentLocation();
        }

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                subscribeObservers();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    vendorFinderViewModel.requestUserCurrentLocation();
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void subscribeObservers() {
        binding.distnaceLabel.setDrawableLeftClickListener(new DrawableTextView.DrawableLeftClickListener() {
            @Override
            public void onDrawableLeftClickListener(View view) {
                clearFilter();

            }
        });
        binding.distnaceLabel.setDrawableRightClickListener(new DrawableTextView.DrawableRightClickListener() {
            @Override
            public void onDrawableRightClickListener(View view) {
                clearFilter();
            }
        });
        vendorFinderViewModel.observeUserLocation().observe(getViewLifecycleOwner(), new Observer<UserLocation>() {
            @Override
            public void onChanged(UserLocation userLocation) {
//                binding.addressLabel.setText(userLocation.getAddress() != null &&
//                        !userLocation.getAddress().isEmpty() ?
//                        userLocation.getAddress() :
//                        getString(R.string.unnamed_place));
                String addressLine = userLocation.prepareAddressData(userLocation.getCurrentAddress());
                binding.addressLabel.setText(userLocation.getCurrentAddress().getAddressLine(0) != null ? addressLine/*userLocation.getCurrentAddress().getAddressLine(0)*/ : getString(R.string.unnamed_place));

                currentUserLocation = userLocation;

                observeVendors(market_type_id,
                        userLocation.getLatitude() + "",
                        userLocation.getLongitude() + "",
                        currentDistance);


                binding.refresh.setRefreshing(false);
            }
        });


    }


    public void observeVendors(int marketType,
                               String latitude,
                               String longitude,
                               int distance) {
        vendorFinderViewModel.observeVendors(marketType, latitude, longitude, distance).observe(getViewLifecycleOwner(), new Observer<Resource<VendorModel>>() {
            @Override
            public void onChanged(Resource<VendorModel> vendorModelResource) {

                switch (vendorModelResource.status) {
                    case ERROR:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.errLabel.setVisibility(View.VISIBLE);
                        binding.vendorRecycle.setVisibility(View.GONE);

                        Log.d(TAG, "onChanged: ERROR");
                        Log.d(TAG, "onChanged: ERROR-Message" + vendorModelResource.message);

                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        binding.vendorRecycle.setVisibility(View.GONE);
                        binding.errLabel.setVisibility(View.GONE);
                        Log.d(TAG, "onChanged: LOADING");
                        break;
                    case SUCCESS:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.vendorRecycle.setVisibility(View.VISIBLE);
                        binding.errLabel.setVisibility(View.GONE);
                        Log.d(TAG, "onChanged: SUCCESS");

                        if (vendorModelResource.data.getResult() != null && vendorModelResource.data.getResult().size() > 0) {
                            adapter.submitList(vendorModelResource.data.getResult());
                            binding.vendorCounterLabel.setText(vendorModelResource.data.getResult().size() + "");
                        } else {
                            binding.vendorRecycle.setVisibility(View.INVISIBLE);
                            binding.errLabel.setText(getString(R.string.no_data_available));
                            binding.errLabel.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                binding.refresh.setRefreshing(false);
            }
        });
    }

    private void setupRecycleView() {
        binding.vendorRecycle.setHasFixedSize(true);
        binding.vendorRecycle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new VendorListAdapter();
        adapter.setClickListener(this);
        binding.vendorRecycle.setAdapter(adapter);
        binding.vendorRecycle.setItemAnimator(new DefaultItemAnimator());

    }

    private void clearFilter() {
        binding.fitlerTitleLabel.setText(getString(R.string.all_stores));
        currentDistance = 0;
        binding.distnaceLabel.setText("");
        binding.distnaceLabel.setVisibility(View.GONE);

        vendorFinderViewModel.searchNearestVendor(market_type_id,
                currentUserLocation.getLatitude() + "",
                currentUserLocation.getLongitude() + "",
                currentDistance);
        binding.refresh.setRefreshing(false);
    }

    public void showFilterSheet() {
        FilterVendorSheetDailog sheet = FilterVendorSheetDailog.newInstance(currentDistance);
        sheet.setListener(this);
        sheet.show(getActivity().getSupportFragmentManager(), FilterVendorSheetDailog.TAG);
    }


    @Override
    public void onFilterByDistance(int distance) {
        currentDistance = distance;
        if (currentDistance > 0) {
            binding.distnaceLabel.setVisibility(View.VISIBLE);
            binding.distnaceLabel.setText(String.format(getString(R.string.distance_placeholder_without_fraction), distance));

            binding.fitlerTitleLabel.setText(getString(R.string.store_filter));
        } else {
            binding.distnaceLabel.setVisibility(View.GONE);
            binding.fitlerTitleLabel.setText(getString(R.string.all_stores));
        }
        vendorFinderViewModel.searchNearestVendor(market_type_id,
                currentUserLocation.getLatitude() + "",
                currentUserLocation.getLongitude() + "",
                distance);
    }


    @Override
    public void clickVendor(int position) {
        VendorFinderFrgamentDirections.ActionVendorFinderFrgamentToCategoryProductsFragment action =
                VendorFinderFrgamentDirections.actionVendorFinderFrgamentToCategoryProductsFragment(adapter.getCurrentList().get(position).getName());
        action.setBranchId(adapter.getCurrentList().get(position).getId());
        navController.navigate(action);
    }

    public void navigateToSearch() {
        navController.navigate(R.id.action_vendorFinderFrgament_to_searchFragment);
    }
}
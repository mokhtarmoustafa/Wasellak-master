package com.unicom.wasalakclientproduct.ui.branchDetails;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentBranchDetailsBinding;
import com.unicom.wasalakclientproduct.model.branch.BranchResponse;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Result;
import com.unicom.wasalakclientproduct.model.user.UserLocation;
import com.unicom.wasalakclientproduct.ui.category.CategoryAdapter;
import com.unicom.wasalakclientproduct.ui.category.CategoryProductsFragmentDirections;
import com.unicom.wasalakclientproduct.ui.category.CategoryProductsFragmentViewModel;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;
import com.unicom.wasalakclientproduct.utils.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.qualifiers.ApplicationContext;

@AndroidEntryPoint
public class BranchDetailsFragment extends Fragment implements BranchCategoryAdapter.CategoryClickListener {

    //region Members
    private final String TAG = this.getClass().getSimpleName();
    FragmentBranchDetailsBinding binding;
    private NavController navController;
    @Inject
    public PreferenceUtils preference;
    private BranchResponse branchData;
    private UserLocation currentUserLocation;
    private BranchDetailsFragmentViewModel branchDetailsFragmentViewModel;
    private CategoryProductsFragmentViewModel categoryProductsFragmentViewModel;
    private Result result = new Result();
    BranchCategoryAdapter categoryAdapter;

    //endregion
//region Events


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        branchDetailsFragmentViewModel = new ViewModelProvider(this).get(BranchDetailsFragmentViewModel.class);
        categoryProductsFragmentViewModel = new ViewModelProvider(this).get(CategoryProductsFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_branch_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            branchDetailsFragmentViewModel.requestUserCurrentLocation();
        }

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBranchData();
            }
        });

        binding.setLifecycleOwner(this);
        binding.setFragment(this);


        getBranchData();


    }

    @Override
    public void clickCategoryData(Category category) {
//        int category = position;
        navigateToCategoryProducts(category);
    }

    //endregion
//region Helper Functions

    private void getBranchData() {
        if (getArguments() != null) {
            {

                branchData = BranchDetailsFragmentArgs.fromBundle(getArguments()).getBranchDetail();
                binding.setBranchData(branchData.getResult());
                result = branchData.getResult();
                if (result.getLatitude() != null && !result.getLatitude().isEmpty() && result.getLongitude() != null && !result.getLongitude().isEmpty())
                    subscribeObservers(Double.parseDouble(result.getLatitude()), Double.parseDouble(result.getLongitude()));
//        binding.tvRateValue.setText();
//        binding.ratingBar.setRating();


                categoryAdapter = new BranchCategoryAdapter(Category.itemCallback);
                categoryAdapter.setCategoryClickListener(this);
                categoryAdapter.submitList(result.getCategories());
                binding.setCategoryAdapter(categoryAdapter);
                binding.refresh.setRefreshing(false);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void subscribeObservers(double storeLat, double storeLong) {
        branchDetailsFragmentViewModel.observeUserLocation().observe(getViewLifecycleOwner(), new Observer<UserLocation>() {
            @Override
            public void onChanged(UserLocation userLocation) {
                currentUserLocation = userLocation;
                double data = getDistance(currentUserLocation.getLatitude(), currentUserLocation.getLongitude(),
                        storeLat, storeLong);
                binding.tvAddressKm.setText(StringUtil.getString(Math.round(data)) + " " + getString(R.string.total_km));
            }
        });


    }

    public void navigateToCategoryProducts(Category category) {
        BranchDetailsFragmentDirections.ActionBranchDetailsFragmentToProductVendorListFragment action =
                BranchDetailsFragmentDirections.actionBranchDetailsFragmentToProductVendorListFragment(branchData, category.getCategoryId(), category.getDisplayName());
        navController.navigate(action);

    }


    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    public void navigateToSearch()
    {
        navController.navigate(R.id.action_branchDetailsFragment_to_searchFragment);
    }
    //endregion
}
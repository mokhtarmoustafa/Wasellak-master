package com.unicom.wasalakclientproduct.ui.productDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentProductSpecificationsBinding;

import com.unicom.wasalakclientproduct.model.productDetails.ProductProperity;
import com.unicom.wasalakclientproduct.model.productDetails.Property;
import com.unicom.wasalakclientproduct.model.productDetails.PropertyValue;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductSpecificationsFragment extends Fragment {

    //region Members
    private final String TAG = getClass().getSimpleName();
    FragmentProductSpecificationsBinding binding;
    ProductDetailViewModel productDetailViewModel;
    private NavController navController;
    ProductSpecificationAdapter adapter;
    @Inject
    public PreferenceUtils preference;

    //endregion

    //region Events

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productDetailViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_specifications, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ProductDetailFragmentArgs args = ProductDetailFragmentArgs.fromBundle(getArguments());
        if (Constants.PRODUCT_RESPONSE != null)
            getProductProperties();
        else
            binding.tvEmptyData.setVisibility(View.VISIBLE);
    }

    //endregion

    //region Helper Functions
    private void getProductProperties() {
        binding.rvProductSpecifications.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new ProductSpecificationAdapter(R.layout.prdouct_specification_list_row, ProductProperity.itemCallback);
//        adapter.submitList(Constants.PRODUCT_RESPONSE.getResult().getProductProperities());

        //  binding.setProductSpecificationAdapter(adapter);
        binding.rvProductSpecifications.setAdapter(adapter);
        adapter.submitList(Constants.PRODUCT_RESPONSE.getResult().getProductProperities());

    }
    //endregion
}
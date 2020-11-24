package com.unicom.wasalakclientproduct.ui.product_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentCategoryVendorListBinding;
import com.unicom.wasalakclientproduct.model.branch.BranchResponse;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.model.category.ProductVendorResponse;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.ui.vendor.Resource;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class ProductVendorListFragment extends Fragment implements ProductVendorListAdapter.ClickListener {
    private FragmentCategoryVendorListBinding binding;
    private ProductVendorListAdapter adapter;
    private ProductVendorListViewModel productListViewModel;
    private ProductVendorListFragmentArgs args;
    private int cateogryId;
    private NavController navController;
   private int branchId;
    @Inject
    PreferenceUtils preferenceUtils;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_vendor_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productListViewModel = new ViewModelProvider(this).get(ProductVendorListViewModel.class);

        binding.include.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.include.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });

        args = ProductVendorListFragmentArgs.fromBundle(getArguments());

        if (args != null) {
            args = ProductVendorListFragmentArgs.fromBundle(getArguments());
            binding.setBranchData(args.getBraanchDetail());
            branchId = args.getBranchId();
            cateogryId = args.getCategoryId();
            binding.include.setTitle(args.getCategoryName());
            binding.setTitle(args.getCategoryName());
            binding.executePendingBindings();
        }
        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        setupRecycler();
        subscribeObservers();

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productListViewModel.searchAllVendorProducts(false, args.getCategoryId(), null, null, null, null, null, null);

            }
        });
    }

    private void subscribeObservers() {
        productListViewModel.observeVendors(false, cateogryId, null, null, null, null, null, null).observe(getViewLifecycleOwner(), new Observer<Resource<ProductVendorResponse>>() {
            @Override
            public void onChanged(Resource<ProductVendorResponse> productVendorModelResource) {
                switch (productVendorModelResource.status) {
                    case ERROR:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.errLabel.setText(getString(R.string.login_error_msg));
                        binding.errLabel.setVisibility(View.VISIBLE);
                        binding.productRecycle.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        binding.productRecycle.setVisibility(View.GONE);
                        binding.errLabel.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);

                        break;
                    case SUCCESS:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.productRecycle.setVisibility(View.VISIBLE);
                        binding.errLabel.setVisibility(View.GONE);


                        if (productVendorModelResource.data != null) {

                            if (productVendorModelResource.data.getResult().getProductList().size() > 0)
                                binding.errLabel.setVisibility(View.GONE);
                            else {
                                binding.errLabel.setText(getString(R.string.no_data_available));
                                binding.errLabel.setVisibility(View.VISIBLE);
                            }

                            adapter.submitList(productVendorModelResource.data.getResult().getProductList());
                            binding.productCountLabel.setText(String.format(getString(R.string.product_placeholder), productVendorModelResource.data.getResult().getTotalCount()));

                        }
                        binding.refresh.setRefreshing(false);
                        break;
                }
            }
        });


    }

    private void setupRecycler() {

        binding.productRecycle.setHasFixedSize(true);
        binding.productRecycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.productRecycle.setItemAnimator(new DefaultItemAnimator());
        adapter = new ProductVendorListAdapter();
        adapter.setClickListener(this);
        binding.productRecycle.setAdapter(adapter);

    }

    public void navigateToBranchDetails(BranchResponse result) {
        if (result != null) {
            ProductVendorListFragmentDirections.ActionProductVendorListFragmentToBranchDetailsFragment action =
                    ProductVendorListFragmentDirections.actionProductVendorListFragmentToBranchDetailsFragment(result);
            navController.navigate(action);
        }
    }

    @Override
    public void clickProduct(int position) {
        Product product = adapter.getCurrentList().get(position);
        ProductVendorListFragmentDirections.ActionProductVendorListFragmentToProductDetailFragment action =
                ProductVendorListFragmentDirections.actionProductVendorListFragmentToProductDetailFragment(product.getId(), product.getDisplayName());
        action.setBranchId(branchId);
        action.setCategoryId(cateogryId);
        navController.navigate(action);

    }

    @Override
    public void clickAddToCart(int productId, int quantity, double price) {
        productListViewModel.getAddToCartResponse().observe(getViewLifecycleOwner(), new Observer<com.unicom.wasalakclientproduct.utils.Resource<CartModel>>() {
            @Override
            public void onChanged(com.unicom.wasalakclientproduct.utils.Resource<CartModel> addToCartResponseResource) {
                switch (addToCartResponseResource.status) {
                    case ERROR:
                        Toast.makeText(getContext(), addToCartResponseResource.message , Toast.LENGTH_SHORT).show();
                        binding.loadingProgress.setVisibility(View.GONE);
                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        int i = addToCartResponseResource.data.getResult().getTotalProduct();
                        preferenceUtils.setCartCount(i);
                        ((UserActivity) getActivity()).badgeDrawable.setNumber(i);
                        ((UserActivity) getActivity()).badgeDrawable.setVisible(true);
                        Toasty.success(getContext(), "تم اضافه المنتج للسلة", Toasty.LENGTH_SHORT).show();
                        binding.loadingProgress.setVisibility(View.GONE);
                        break;
                }
            }
        });
        productListViewModel.addToCart(new AddToCartDTO(branchId , productId , cateogryId , quantity , price));
    }



    public void navigateToSearch()
    {
        navController.navigate(R.id.action_productVendorListFragment_to_searchFragment);
    }
}

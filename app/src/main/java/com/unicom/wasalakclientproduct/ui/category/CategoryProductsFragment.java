package com.unicom.wasalakclientproduct.ui.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentCategoryProductsBinding;
import com.unicom.wasalakclientproduct.model.branch.BranchResponse;

import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Result;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.ui.branchDetails.CategoryMenuAdapter;
import com.unicom.wasalakclientproduct.ui.branchDetails.CategoryMenuDialog;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;


@AndroidEntryPoint
public class CategoryProductsFragment extends Fragment implements CategoryAdapter.CategoryClickListener, CategoryMenuDialog.ICategoryClicked, ParentAdapter.CategoryClickListener, CategoryMenuAdapter.CategoryClickListener {


    //region Members
    private final String TAG = this.getClass().getSimpleName();
    private CategoryProductsFragmentViewModel categoryProductsFragmentViewModel;
    FragmentCategoryProductsBinding binding;
    private NavController navController;
    private Result result = new Result();
    CategoryMenuDialog categoryMenuDialog;
    private BranchResponse branchData;
    CategoryAdapter categoryAdapter;
    ParentAdapter parentAdapter;
    private int branch_id;
    @Inject
    PreferenceUtils preferenceUtils;
    //endregion

    //region Events

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryProductsFragmentViewModel = new ViewModelProvider(this).get(CategoryProductsFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_products, container, false);
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

        if (getArguments() != null) {
            branch_id = CategoryProductsFragmentArgs.fromBundle(getArguments()).getBranchId();
            binding.topAppBar.actionBar.setTitle(CategoryProductsFragmentArgs.fromBundle(getArguments()).getBranchName());
        }

        binding.setLifecycleOwner(this);
        binding.setViewModel(categoryProductsFragmentViewModel);
        binding.setFragment(this);

        getBranchDetails(branch_id, null, null);

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBranchDetails(branch_id, null, null);

            }
        });

    }


    @Override
    public void clickCategoryData(Category category) {
        navigateToCategoryProducts(category);
    }

    @Override
    public void onFilterCategoryClicked(Category category) {
        navigateToCategoryProducts(category);
    }

    @Override
    public void onCategorySelected(Category category) {
        CategoryProductsFragmentDirections.ActionCategoryProductsFragmentToProductVendorListFragment action =
                CategoryProductsFragmentDirections.actionCategoryProductsFragmentToProductVendorListFragment(branchData, category.getCategoryId(), category.getDisplayName());
        navController.navigate(action);
    }

    //endregion
    //region Helper Functions


    private void getBranchDetails(Integer branchId, String latitude, String longitude) {

        categoryProductsFragmentViewModel.getBranchDetailsLiveData(branchId, latitude, longitude).observe(getViewLifecycleOwner(), new Observer<Resource<BranchResponse>>() {
            @Override
            public void onChanged(Resource<BranchResponse> branchResponse) {
                switch (branchResponse.status) {
                    case ERROR:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvCategoryProducts.setVisibility(View.GONE);
                        binding.tvEmptyData.setText(getString(R.string.login_error_msg));
                        binding.tvEmptyData.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        binding.rvCategoryProducts.setVisibility(View.GONE);
                        binding.tvEmptyData.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);

                        break;
                    case SUCCESS:
                        Log.d(TAG, "N: $categoryResponseResource");
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.rvCategoryProducts.setVisibility(View.VISIBLE);
                        binding.tvEmptyData.setVisibility(View.INVISIBLE);
                        binding.refresh.setRefreshing(false);

                        branchData = branchResponse.data;
                        result = branchResponse.data.getResult();
                        Constants.RESULT_DATA = result;

                        if(branchData.getResult()!=null&&branchData.getResult().getCategories().size()>0)
                        {
                            binding.setBranchData(branchResponse.data);
                            parentAdapter = new ParentAdapter(Category.itemCallback , branch_id,branchData.getResult().getTotalBranchProducts());
                            parentAdapter.setCategoryClickListener(CategoryProductsFragment.this);
                            parentAdapter.submitList(branchResponse.data.getResult().getCategories());
                            binding.setParentAdapter(parentAdapter);

                            categoryAdapter = new CategoryAdapter(R.layout.layout_category, Category.itemCallback);
                            categoryAdapter.setCategoryClickListener(CategoryProductsFragment.this);
                            categoryAdapter.submitList(branchResponse.data.getResult().getCategories());
                            binding.setCategoryAdapter(categoryAdapter);
                        }
                        break;
                }

            }
        });
    }

    public void navigateToBranchDetails(BranchResponse result) {
//        Log.d(TAG, "navigateToBranchDetails: " + branch.getName());
        if (result != null) {
            CategoryProductsFragmentDirections.ActionCategoryProductsFragmentToBranchDetailsFragment action =
                    CategoryProductsFragmentDirections.actionCategoryProductsFragmentToBranchDetailsFragment(result);
            navController.navigate(action);
        }

    }

    public void showFilterDialog() {
        if (branchData.getResult().getCategories() != null && branchData.getResult().getCategories().size() > 0) {
            categoryMenuDialog = new CategoryMenuDialog();
            categoryMenuDialog.result = Constants.RESULT_DATA;
            categoryMenuDialog.setListener(this::onFilterCategoryClicked);
            categoryMenuDialog.show(requireActivity().getSupportFragmentManager(), categoryMenuDialog.getTag());

        }
    }

    @Override
    public void clickCategory(int position) {
        Category category = parentAdapter.getCurrentList().get(position);
        navigateToCategoryProducts(category);
    }

    public void navigateToCategoryProducts(Category category) {
        CategoryProductsFragmentDirections.ActionCategoryProductsFragmentToProductVendorListFragment action =
                CategoryProductsFragmentDirections.actionCategoryProductsFragmentToProductVendorListFragment(branchData ,  category.getCategoryId(), category.getDisplayName());
       action.setBranchId(branch_id);
        navController.navigate(action);

    }

    @Override
    public void clickProductData(Integer id, String name, int branchId, int categoryId) {
        CategoryProductsFragmentDirections.ActionCategoryProductsFragmentToProductDetailFragment action =
                CategoryProductsFragmentDirections.actionCategoryProductsFragmentToProductDetailFragment(id , name);
        action.setBranchId(branchId);
        action.setCategoryId(categoryId);
        navController.navigate(action);
    }

    @Override
    public void clickAddToCart(int productId, int branchId, int categoryId, int quantity, double price) {
        categoryProductsFragmentViewModel.getAddToCartResponse().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> addToCartResponseResource) {
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
        categoryProductsFragmentViewModel.addToCart(new AddToCartDTO(branchId , productId , categoryId , quantity , price));
    }


    public void navigateToSearch()
    {
        navController.navigate(R.id.action_categoryProductsFragment_to_searchFragment);
    }
}
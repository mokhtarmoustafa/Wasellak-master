package com.unicom.wasalakclientproduct.ui.search;

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
import com.unicom.wasalakclientproduct.databinding.FragmentSearchResultBinding;
import com.unicom.wasalakclientproduct.model.branch.BranchResponse;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.model.category.ProductVendorResponse;
import com.unicom.wasalakclientproduct.model.search.Search;
import com.unicom.wasalakclientproduct.model.search.SearchResponse;
import com.unicom.wasalakclientproduct.model.search.Store;
import com.unicom.wasalakclientproduct.ui.product_list.ProductVendorListViewModel;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.ui.vendor.FilterVendorSheetDailog;
import com.unicom.wasalakclientproduct.ui.vendor.Resource;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;

@AndroidEntryPoint
public class SearchResultFragment extends Fragment implements FilterSearchSheetDialog.OnFilterListener, StoreSearchListAdapter.ClickListener, SearchProductsAdapter.ClickListener {
    //region Members
    String TAG = this.getClass().getSimpleName();
    public SearchProductsAdapter searchProductsAdapter;
    public StoreSearchListAdapter storeSearchListAdapter;
    private NavController navController;
    SearchViewModel viewModel;
    private ProductVendorListViewModel productListViewModel;
    FragmentSearchResultBinding binding;
    private int currentDistance = 0;
    private int cateogryId;
    private int branchId;
    private Search searchData;
    FilterSearchSheetDialog sheet;
    @Inject
    PreferenceUtils preferenceUtils;

    //endregion

    //region Events

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        getSearchData();
    }


    @Override
    public void onFilterClicked(int distance, int storeId, int activityId, Boolean isWithDiscount) {
        subscribeFilter(distance, storeId, activityId, isWithDiscount);
    }

    @Override
    public void clickVendor(int position) {

        SearchResultFragmentDirections.ActionSearchResultFragmentToCategoryProductsFragment action =
                SearchResultFragmentDirections.actionSearchResultFragmentToCategoryProductsFragment(storeSearchListAdapter.getCurrentList().get(position).getDisplayName());
        action.setBranchId(storeSearchListAdapter.getCurrentList().get(position).getNearestBranchId());
        navController.navigate(action);

    }

    @Override
    public void clickProduct(int position) {
        Product product = searchProductsAdapter.getCurrentList().get(position);
        SearchResultFragmentDirections.ActionSearchResultFragmentToProductDetailFragment action =
                SearchResultFragmentDirections.actionSearchResultFragmentToProductDetailFragment(product.getId(), product.getDisplayName());
        action.setBranchId(branchId);
        action.setCategoryId(product.getCategoryId());
        navController.navigate(action);
    }

    @Override
    public void clickAddToCart(int productId, int quantity, double price) {
        productListViewModel.getAddToCartResponse().observe(getViewLifecycleOwner(), new Observer<com.unicom.wasalakclientproduct.utils.Resource<CartModel>>() {
            @Override
            public void onChanged(com.unicom.wasalakclientproduct.utils.Resource<CartModel> addToCartResponseResource) {
                switch (addToCartResponseResource.status) {
                    case ERROR:
                        Toast.makeText(getContext(), addToCartResponseResource.message, Toast.LENGTH_SHORT).show();
                        binding.progressBarFrame.setVisibility(View.GONE);
                        break;
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        int i = addToCartResponseResource.data.getResult().getTotalProduct();
                        preferenceUtils.setCartCount(i);
                        ((UserActivity) getActivity()).badgeDrawable.setNumber(i);
                        ((UserActivity) getActivity()).badgeDrawable.setVisible(true);
                        Toasty.success(getContext(), "تم اضافه المنتج للسلة", Toasty.LENGTH_SHORT).show();
                        binding.progressBarFrame.setVisibility(View.GONE);
                        break;
                }
            }
        });
        productListViewModel.addToCart(new AddToCartDTO(branchId, productId, cateogryId, quantity, price));
    }

    //endregion

    //region Helper Functions

    private void init(@NonNull View view) {
        Log.d(TAG, "onViewCreated: ");
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        productListViewModel = new ViewModelProvider(this).get(ProductVendorListViewModel.class);


        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setFragment(this);
        binding.setViewModel(viewModel);

        binding.toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (getArguments() != null) {
                    searchData = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchData();
                    viewModel.productName.setValue(searchData.getName());
                    subscribeObservers();
                }
            }
        });
    }

    public void navigateToSearch() {
        navController.navigate(R.id.action_searchResultFragment_to_searchFragment);
    }


    private void getSearchData() {

        if (getArguments() != null) {
            searchData = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchData();
            viewModel.productName.setValue(searchData.getName());
            subscribeObservers();
        }


    }


    private void setStoreData(List<Store> storeList) {

        storeSearchListAdapter = new StoreSearchListAdapter();
        storeSearchListAdapter.setClickListener(this);
        storeSearchListAdapter.submitList(storeList);
        binding.setStoreAdapter(storeSearchListAdapter);
        binding.tvStoreSearchResultData.setText(searchData.getName() != null ? String.format(getString(R.string.search_store_result), storeList.size(), searchData.getName()) : "");
    }

    private void setProductsData(List<Product> productsList) {
        searchProductsAdapter = new SearchProductsAdapter();
        searchProductsAdapter.setClickListener(this);
        searchProductsAdapter.submitList(productsList);
        binding.setProductAdapter(searchProductsAdapter);
        binding.tvProductsSearchResultData.setText(searchData.getName() != null ? String.format(getString(R.string.search_products_result), productsList.size(), searchData.getName()) : "");
    }

    public void showFilterSheet() {
        sheet = FilterSearchSheetDialog.newInstance(currentDistance);
        sheet.setListener(this);
        sheet.show(getActivity().getSupportFragmentManager(), FilterVendorSheetDailog.TAG);
    }


    private void subscribeObservers() {

        viewModel.observeSearchResultData().observe(getViewLifecycleOwner(), new Observer<Resource<SearchResponse>>() {
            @Override
            public void onChanged(Resource<SearchResponse> searchResponse) {
                switch (searchResponse.status) {
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        binding.rvSearchStore.setVisibility(View.GONE);
                        binding.rvSearchProducts.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        binding.rvSearchStore.setVisibility(View.GONE);
                        binding.rvSearchProducts.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case SUCCESS:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        binding.rvSearchStore.setVisibility(View.VISIBLE);
                        binding.rvSearchProducts.setVisibility(View.VISIBLE);
                        binding.line1.setVisibility(View.VISIBLE);
                        binding.tvEmptyData.setVisibility(View.GONE);
                        if (searchResponse.data != null) {
                            if (searchResponse.data.getResult().getStores() != null && searchResponse.data.getResult().getStores().size() > 0)
                                setStoreData(searchResponse.data.getResult().getStores());
                            if (searchResponse.data.getResult().getProducts() != null && searchResponse.data.getResult().getProducts().size() > 0)
                                setProductsData(searchResponse.data.getResult().getProducts());
                            else {
                                binding.line1.setVisibility(View.INVISIBLE);
                                binding.tvEmptyData.setVisibility(View.VISIBLE);
                            }


                        }

                        break;
                }

            }
        });


    }

    private void subscribeFilter(int distance, int storeId, int activityId, Boolean isWithDiscount) {
        viewModel.ObserveFilterSearchResultData(distance, storeId, activityId, isWithDiscount).observe(getViewLifecycleOwner(), new Observer<Resource<SearchResponse>>() {
            @Override
            public void onChanged(Resource<SearchResponse> searchResponse) {
                switch (searchResponse.status) {
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        binding.rvSearchStore.setVisibility(View.GONE);
                        binding.rvSearchProducts.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        binding.rvSearchStore.setVisibility(View.GONE);
                        binding.rvSearchProducts.setVisibility(View.GONE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case SUCCESS:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        binding.rvSearchStore.setVisibility(View.VISIBLE);
                        binding.rvSearchProducts.setVisibility(View.VISIBLE);
                        if (searchResponse.data != null) {
                            if (searchResponse.data.getResult().getStores() != null && searchResponse.data.getResult().getStores().size() > 0)
                                setStoreData(searchResponse.data.getResult().getStores());
                            if (searchResponse.data.getResult().getProducts() != null && searchResponse.data.getResult().getProducts().size() > 0)
                                setProductsData(searchResponse.data.getResult().getProducts());
                        }

                        break;
                }

            }
        });

    }

    public void navigateToBranchDetails(BranchResponse result) {
        if (result != null) {
            SearchResultFragmentDirections.ActionSearchResultFragmentToBranchDetailsFragment action = SearchResultFragmentDirections.actionSearchResultFragmentToBranchDetailsFragment(result);
            navController.navigate(action);
        }
    }
    //endregion
}


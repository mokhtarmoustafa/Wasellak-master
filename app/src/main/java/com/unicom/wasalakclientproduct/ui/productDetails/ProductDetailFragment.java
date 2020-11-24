package com.unicom.wasalakclientproduct.ui.productDetails;

import android.os.Bundle;
import android.util.Log;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.adapter.QuntityProductAdpater;
import com.unicom.wasalakclientproduct.databinding.FragmentProductDetailBinding;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.AddToCartDTO;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.model.Quantity;
import com.unicom.wasalakclientproduct.model.productDetails.ProductAttachment;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.utils.Constants;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;


@AndroidEntryPoint
public class ProductDetailFragment extends Fragment implements AddProductSheetDialog.OnAddProductClickListener, QuntityProductAdpater.ClickListener {
    private static final String TAG = "ProductDetailFragment";
    private FragmentProductDetailBinding binding;
    ProductDetailViewModel viewModel;
    ProductResponse productData;
    private NavController navController;
    private ImageProductSlider imageAdapter;
    QuntityProductAdpater quantityAdapter;
    ProductDetailFragmentArgs args;
    int productId, categoryId, branchId;
    double productPrice;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        binding.setLifecycleOwner(this);
        binding.cartContainer.setFragment(this);

        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.topAppBar.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });


        binding.topAppBar.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSearch();
            }
        });

        if (getArguments() != null) {
            args = ProductDetailFragmentArgs.fromBundle(getArguments());
            binding.topAppBar.setTitle(args.getProductName());
            productId = args.getProductId();
            branchId = args.getBranchId();
            categoryId = args.getCategoryId();
            getProductDetails(productId);
        }

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (getArguments() != null) {
                    args = ProductDetailFragmentArgs.fromBundle(getArguments());
                    binding.topAppBar.setTitle(args.getProductName());
                    getProductDetails(productId);
                }
            }
        });

        imageAdapter = new ImageProductSlider(getActivity());



        binding.fragmentPager.setAdapter(new ScreenSlidePagerAdapter(getActivity()));
        new TabLayoutMediator(binding.tabLayout, binding.fragmentPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(getString(R.string.over_view));
                        break;
                    case 1:
                        tab.setText(getString(R.string.specefication));

                        break;
                    case 2:
                        tab.setText(getString(R.string.ratings));
                        break;
                }
            }
        }).attach();


        binding.cartContainer.rlQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.cartContainer.quantityRecycle.getVisibility() == View.GONE) {
                    binding.cartContainer.ivAction.setImageResource(R.drawable.ic_down);
                    binding.cartContainer.quantityRecycle.setVisibility(View.VISIBLE);
                } else {
                    binding.cartContainer.ivAction.setImageResource(R.drawable.ic_up);
                    binding.cartContainer.quantityRecycle.setVisibility(View.GONE);
                }
            }
        });
    }


    private void getProductDetails(int id) {
        viewModel.observeProductDetailsLiveData(id).observe(getViewLifecycleOwner(), new Observer<Resource<ProductResponse>>() {
            @Override
            public void onChanged(Resource<ProductResponse> productResponse) {
                switch (productResponse.status) {
                    case ERROR:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.container.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);
                        break;
                    case LOADING:
                        binding.loadingProgress.setVisibility(View.VISIBLE);
                        binding.container.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);

                        break;
                    case SUCCESS:
                        binding.loadingProgress.setVisibility(View.GONE);
                        binding.container.setVisibility(View.VISIBLE);
                        binding.refresh.setRefreshing(false);

                        productData = productResponse.data;
                        ArrayList<String> sliders=new ArrayList<>();
                        for (ProductAttachment slider : productData.getResult().getProductAttachments()) {
                            sliders.add(slider.getPath());
                        }
                        imageAdapter.renewItems(sliders);
                        binding.imageSlider.setSliderAdapter(imageAdapter);
                        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
                        Constants.PRODUCT_RESPONSE = productData;
                        binding.setProductData(productData);
                        loadProductQuantity(productData.getResult().getQuantity());
                        productPrice = productData.getResult().getPrice();
                        break;
                }

            }
        });
    }
    AddProductSheetDialog sheetDialog;
    public void showAddCartSheet(double total_price) {
        sheetDialog = new AddProductSheetDialog(productData , total_price);
        sheetDialog.setListener(this);
        sheetDialog.show(getParentFragmentManager(), AddProductSheetDialog.TAG);
    }

    public void clickAddToCart() {
        viewModel.getAddToCartResponse().observe(getViewLifecycleOwner(), new Observer<com.unicom.wasalakclientproduct.utils.Resource<CartModel>>() {
            @Override
            public void onChanged(com.unicom.wasalakclientproduct.utils.Resource<CartModel> addToCartResponseResource) {
                switch (addToCartResponseResource.status) {
                    case ERROR:
                        Toast.makeText(getContext(), addToCartResponseResource.message, Toast.LENGTH_SHORT).show();
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
                        showAddCartSheet(addToCartResponseResource.data.getResult().getTotalPrice());
                        break;
                }
            }
        });
        viewModel.addToCart(new AddToCartDTO(branchId, productId, categoryId, (quantity == null || quantity.isEmpty()) ? 1 : Integer.parseInt(quantity), productPrice));
    }
    @Inject
    PreferenceUtils preferenceUtils;
    @Override
    public void onResumeShopping(String specialOfferText) {
        Log.d(TAG, "onResumeShopping: " + specialOfferText);
        sheetDialog.dismiss();
    }

    @Override
    public void onNavigateToCart(String specialOfferText) {
        Log.d(TAG, "onResumeShopping: " + specialOfferText);
    }

    String quantity;
    @Override
    public void clickMarket(int position) {
        quantity = quantityAdapter.getCurrentList().get(position).getNum();
        binding.cartContainer.etQuantity.setText(quantity);
        binding.cartContainer.quantityRecycle.setVisibility(View.GONE);
        binding.cartContainer.ivAction.setImageResource(R.drawable.ic_up);
    }

    private void loadProductQuantity(int totalQuantity) {
        if (totalQuantity > 0) {
            quantityAdapter = new QuntityProductAdpater();
            quantityAdapter.setClickListener(this);
            binding.cartContainer.quantityRecycle.setAdapter(quantityAdapter);
            ArrayList<Quantity> quantities = new ArrayList<>();
            for (int i = 1; i <= totalQuantity; i++) {
                if (i == 1){
                    quantities.add(new Quantity(String.valueOf(i), true));
                    continue;
                }
                quantities.add(new Quantity(String.valueOf(i), false));
            }

            quantityAdapter.submitList(quantities);
        } else {
            binding.cartContainer.etQuantity.setText("0");
            binding.cartContainer.etQuantity.setEnabled(false);
            binding.cartContainer.ivAction.setEnabled(false);
        }


    }

    public void navigateToSearch()
    {
        navController.navigate(R.id.action_productDetailFragment_to_searchFragment);
    }
}
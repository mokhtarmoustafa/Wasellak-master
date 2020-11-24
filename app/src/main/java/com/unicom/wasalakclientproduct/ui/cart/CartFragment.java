package com.unicom.wasalakclientproduct.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentCartBinding;
import com.unicom.wasalakclientproduct.model.AddressClass;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.CartOperationsResponse;
import com.unicom.wasalakclientproduct.ui.user.UserActivity;
import com.unicom.wasalakclientproduct.utils.PreferenceUtils;
import com.unicom.wasalakclientproduct.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;


@AndroidEntryPoint
public class CartFragment extends Fragment implements ProductCartAdapter.ClickListener {

    //region Members
    String TAG = this.getClass().getSimpleName();
    FragmentCartBinding binding;
    CartViewModel viewModel;
    private NavController navController;
    @Inject
    ProductCartAdapter adapter;

    //endregion
    //region Events

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (navController == null) {
            navController = Navigation.findNavController(view);
        }
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCartViewModel(viewModel);
        adapter.setClickListener(this);
        binding.setAdapter(adapter);
        binding.setFragment(this);
        observeCartResponse();
    }

    @Inject
    PreferenceUtils preferenceUtils;
    List<CartModel.CartsDetail> cartsDetails_old;

    public void onClickBuy(List<CartModel.Addresses> addresses) {
        if (addresses.size() == 0) {
            navController.navigate(R.id.action_cartFragment_to_addressesFragment);
            Toasty.error(getContext(), "please add at least one address", Toasty.LENGTH_LONG).show();
            return;
        }
        if (viewModel.addressId.getValue() == null) {

            navController.navigate(R.id.action_cartFragment_to_addressesFragment);
            Toasty.error(getContext(), "please choose delivery address", Toasty.LENGTH_LONG).show();
            return;
        }
        observeCarChooseAddress();
    }

    private void observeCarChooseAddress() {
        viewModel.observeCartAddress().observe(getViewLifecycleOwner(), new Observer<Resource<CartOperationsResponse>>() {
            @Override
            public void onChanged(Resource<CartOperationsResponse> cartOperationsResponseResource) {
                switch (cartOperationsResponseResource.status) {
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        Toasty.error(getContext(), cartOperationsResponseResource.message, Toasty.LENGTH_LONG).show();
                        break;
                    case SUCCESS:
                        navController.navigate(R.id.action_cartFragment_to_cartConfirmProductsFragment);
                }
            }
        });
        viewModel.chooseAddress(viewModel.addressId.getValue());
    }

    @Override
    public void clickQuantity(String quantity, int productPosition) {
        if (Integer.parseInt(quantity) == adapter.getCurrentList().get(productPosition).getQuantity()) {
            return;
        }
        observeCartChangeQuantityResponse(quantity, productPosition);
    }

    @Override
    public void removeProduct(int product_id, int productPosition) {
        observeCartRemoveResponse(product_id, productPosition);
    }

    public void observeCartResponse() {
        viewModel.observeListProductToCart().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartModelResource) {
                switch (cartModelResource.status) {
                    case ERROR:
                        preferenceUtils.setCartCount(0);
                        ((UserActivity) getActivity()).badgeDrawable.setVisible(false);
                        break;
                    case SUCCESS:
                        preferenceUtils.setCartCount(cartModelResource.data.getResult().getTotalProduct());
                        ((UserActivity) getActivity()).badgeDrawable.setNumber(cartModelResource.data.getResult().getTotalProduct());
                        ((UserActivity) getActivity()).badgeDrawable.setVisible(true);
                        adapter.submitList(cartModelResource.data.getResult().getCartsDetails());
                        getAddresses(cartModelResource.data.getResult().getAddresses());
//                        cartsDetails_old.addAll(cartModelResource.data.getResult().getCartsDetails());
                        break;
                }
            }
        });
    }


    private void getAddresses(List<CartModel.Addresses> addresses) {
        List<AddressClass> addressesSpiner = new ArrayList<>();
        for (CartModel.Addresses address : addresses) {
            addressesSpiner.add(new AddressClass(address.getId(), address.getDescription()));
        }
        ArrayAdapter<AddressClass> adapter = new ArrayAdapter<AddressClass>
                (getActivity(), android.R.layout.simple_list_item_1, addressesSpiner);
        binding.mySpinnerDropdown.setThreshold(1);
        binding.mySpinnerDropdown.setAdapter(adapter);
        binding.mySpinnerDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewModel.addressId.setValue(((AddressClass) parent.getItemAtPosition(position)).getId());
            }
        });
    }

    public void observeCartClearResponse() {
        viewModel.observeClearCart().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartModelResource) {
                switch (cartModelResource.status) {
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        Toasty.error(getContext(), cartModelResource.message, Toasty.LENGTH_LONG).show();
                        break;
                }
            }
        });
        viewModel.clearListCartProduct();
    }

    public void observeCartRemoveResponse(int product_id, int productPosition) {
        viewModel.observeRemoveProductToCart().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartOperationsResponseResource) {
                switch (cartOperationsResponseResource.status) {
                    case LOADING:
                        binding.progressBarFrame.setVisibility(View.VISIBLE);
                        break;
                    case ERROR:
                        binding.progressBarFrame.setVisibility(View.GONE);
                        Toasty.error(getContext(), cartOperationsResponseResource.message, Toasty.LENGTH_LONG).show();
                        break;
                    case SUCCESS:
                        if (cartOperationsResponseResource.data.getResult().getCartsDetails().size() == 0) {
                            viewModel.cartResponseLiveData.setValue(Resource.error("Error", null));
                            return;
                        }
                        binding.progressBarFrame.setVisibility(View.GONE);
                        binding.btnDetail.setText(String.format(getString(R.string.products), cartOperationsResponseResource.data.getResult().getTotalProduct()));
                        binding.totalPrice.setText(String.format(getString(R.string.discount_placeholder), cartOperationsResponseResource.data.getResult().getTotalPrice()));
                        binding.totalWithFix.setText(String.format(getString(R.string.discount_placeholder), cartOperationsResponseResource.data.getResult().getTotalPrice() + 100));
                        binding.btnCartBuy.setText(String.format(getString(R.string.distance_nfs), cartOperationsResponseResource.data.getResult().getTotalProduct(), cartOperationsResponseResource.data.getResult().getTotalPrice()));
                        List<CartModel.CartsDetail> cartsDetailList = new ArrayList<>(adapter.getCurrentList());
                        cartsDetailList.remove(productPosition);
                        adapter.submitList(cartsDetailList);
                        break;
                }
            }
        });
        viewModel.removeProduct(product_id);
    }

    private int productPositionActual;

    public void observeCartChangeQuantityResponse(String quantity, int productPosition) {
        this.productPositionActual = productPosition;
        viewModel.observeCartChangeQuantityLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<CartModel>>() {
            @Override
            public void onChanged(Resource<CartModel> cartOperationsResponseResource) {
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
                        binding.totalPrice.setText(String.format(getString(R.string.discount_placeholder), cartOperationsResponseResource.data.getResult().getTotalPrice()));
                        binding.totalWithFix.setText(String.format(getString(R.string.discount_placeholder), cartOperationsResponseResource.data.getResult().getTotalPrice() + 100));
                        binding.btnCartBuy.setText(String.format(getString(R.string.distance_nfs), cartOperationsResponseResource.data.getResult().getTotalProduct(), cartOperationsResponseResource.data.getResult().getTotalPrice()));
                        CartModel.CartsDetail cartsDetail = adapter.getCurrentList().get(productPositionActual);
                        CartModel.CartsDetail cartsDetailUpdate = new CartModel.CartsDetail(cartsDetail.getCartId(), cartsDetail.getProductId(), cartsDetail.getCategoryId(), cartOperationsResponseResource.data.getResult().getCartsDetails().get(productPositionActual).getQuantity(), cartsDetail.getPrice(), cartsDetail.getProduct(), cartsDetail.getId());
                        List<CartModel.CartsDetail> cartsDetailList = new ArrayList<>(adapter.getCurrentList());
                        cartsDetailList.remove(cartsDetail);
                        cartsDetailList.add(productPositionActual, cartsDetailUpdate);
                        adapter.submitList(cartsDetailList);
                        break;
                }
            }
        });
        viewModel.changeQuantity(adapter.getCurrentList().get(productPositionActual).getProductId(), Integer.parseInt(quantity));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
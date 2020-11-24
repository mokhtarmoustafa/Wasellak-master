package com.unicom.wasalakclientproduct.ui.productDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.BottomSheetAddProductDailogBinding;
import com.unicom.wasalakclientproduct.databinding.BottomSheetFilterVendorDailogBinding;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;
import com.unicom.wasalakclientproduct.ui.vendor.FilterVendorSheetDailog;

public class AddProductSheetDialog extends BottomSheetDialogFragment {

    public static final String TAG = "AddProductSheetDialog";
    private BottomSheetAddProductDailogBinding binding;
    private OnAddProductClickListener listener;
    private ProductResponse productResponse;
    private double total_price;

    public AddProductSheetDialog(ProductResponse productResponse , double total_price) {
        this.productResponse = productResponse;
        this.total_price = total_price;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_app_BottomSheetDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =   DataBindingUtil.inflate(inflater,R.layout.bottom_sheet_add_product_dailog,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.setDialog(this);
        binding.setTotalPrice(total_price);
        binding.setProductModel(productResponse);
    }




    public void setListener(OnAddProductClickListener listener) {
        this.listener = listener;
    }

    interface  OnAddProductClickListener{
        void  onResumeShopping(String specialOfferText);
        void  onNavigateToCart(String specialOfferText);
    }

    public   void  onNavigateToCart(String specialOfferText){
         listener.onNavigateToCart(specialOfferText);
         dismiss();
    }
    public void  onBackToShopping(String specialOfferText) {
        {
            listener.onResumeShopping(specialOfferText);
            dismiss();

        }
    }


}
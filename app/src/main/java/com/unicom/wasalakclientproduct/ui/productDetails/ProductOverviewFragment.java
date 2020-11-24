package com.unicom.wasalakclientproduct.ui.productDetails;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentProductDetailBinding;
import com.unicom.wasalakclientproduct.databinding.FragmentProductOverviewBinding;
import com.unicom.wasalakclientproduct.model.productDetails.ProductResponse;
import com.unicom.wasalakclientproduct.utils.Constants;


public class ProductOverviewFragment extends Fragment {

    //region Members
    private final String TAG = this.getClass().getSimpleName();
    FragmentProductOverviewBinding binding;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_overview, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showData(Constants.PRODUCT_RESPONSE);
        binding.tvShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvData.setMaxLines(100);
                binding.tvShowMore.setVisibility(View.GONE);
            }
        });
    }

    private void showData(ProductResponse productData) {
        if (productData != null && productData.getResult().getDescription() != null)
            binding.tvData.setText(getHtmlData(productData.getResult().getDescription().toString()));
        else
            binding.tvShowMore.setVisibility(View.INVISIBLE);

    }



    private SpannableString getHtmlData(String htmlData) {
        SpannableString content = new SpannableString(htmlData);
        return content;
    }
}
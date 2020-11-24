package com.unicom.wasalakclientproduct.ui.productDetails;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutStoreSectionBinding;
import com.unicom.wasalakclientproduct.databinding.PrdouctSpecificationListRowBinding;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.productDetails.ProductProperity;


import dagger.hilt.android.qualifiers.ActivityContext;

public class ProductSpecificationAdapter extends ListAdapter<ProductProperity, RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    Category category;
    private PrdouctSpecificationListRowBinding binding;

    public ProductSpecificationAdapter(int resourceId, @NonNull DiffUtil.ItemCallback<ProductProperity> diffCallback) {
        super(diffCallback);
    }



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ProductSpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

         binding = DataBindingUtil.inflate(layoutInflater, R.layout.prdouct_specification_list_row, parent, false);
        return new ProductSpecificationAdapter.ProductSpecificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position % 2== 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#1A00D472"));
        }
       ProductProperity properity= getItem(position);
        binding.setProductSpecification(properity);
        binding.executePendingBindings();
    }


    class ProductSpecificationViewHolder extends RecyclerView.ViewHolder {
        private final PrdouctSpecificationListRowBinding binding;

        public ProductSpecificationViewHolder(final PrdouctSpecificationListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}

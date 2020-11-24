package com.unicom.wasalakclientproduct.ui.category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryFilterBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutStoreSectionBinding;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.category.Product;


import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class CategoryAdapter extends ListAdapter<Category, RecyclerView.ViewHolder> {
    private CategoryClickListener categoryClickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    Category category;

    public CategoryAdapter(int resourceId, @NonNull DiffUtil.ItemCallback<Category> diffCallback) {
        super(diffCallback);
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutCategoryBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_category, parent, false);
        return new CategoryAdapter.CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        category = getItem(position);
        if (holder instanceof CategoryViewHolder) {
            CategoryViewHolder data = ((CategoryViewHolder) holder);
            data.binding.setCategory(category);
            data.binding.executePendingBindings();
            if (position == (getItemCount() - 1)) {
                data.binding.ivPlaceholder.setVisibility(View.INVISIBLE);
            }

        } else if (holder instanceof CategoryFilterViewHolder) {
            CategoryFilterViewHolder data = ((CategoryFilterViewHolder) holder);
            data.binding.setCategory(category);
            data.binding.executePendingBindings();

        } else if (holder instanceof CategoryBranchDetailsViewHolder) {
            CategoryBranchDetailsViewHolder data = ((CategoryBranchDetailsViewHolder) holder);
            data.binding.tvTotalItems.setText(String.format("%d " + context.getString(R.string.total_products), category.getProductCount()));
            data.binding.setCategory(category);
            data.binding.executePendingBindings();
        }


    }



    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCategoryBinding binding;

        public CategoryViewHolder(final LayoutCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Category category = getItem(getAdapterPosition());
//                    categoryClickListener.clickCategory(getAdapterPosition());
                    Log.d("Category Adapter", "navigateToCategoryProducts: "+category.getCategoryId());
                    categoryClickListener.clickCategoryData(category);
                }
            });
        }

    }

    class CategoryFilterViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCategoryFilterBinding binding;

        public CategoryFilterViewHolder(final LayoutCategoryFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryClickListener.clickCategoryData(category);
                }
            });
        }


    }

    class CategoryBranchDetailsViewHolder extends RecyclerView.ViewHolder {
        private final LayoutStoreSectionBinding binding;

        public CategoryBranchDetailsViewHolder(final LayoutStoreSectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
//            binding.clParent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    categoryClickListener.clickCategory(getAdapterPosition());
//                }
//            });
        }


    }


    public interface CategoryClickListener {
        void clickCategoryData(Category category);
    }
}

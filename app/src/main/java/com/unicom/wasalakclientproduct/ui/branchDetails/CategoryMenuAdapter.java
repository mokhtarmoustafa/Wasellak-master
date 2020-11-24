package com.unicom.wasalakclientproduct.ui.branchDetails;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryFilterBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutStoreSectionBinding;
import com.unicom.wasalakclientproduct.model.branch.Category;

import dagger.hilt.android.qualifiers.ActivityContext;

public class CategoryMenuAdapter extends ListAdapter<Category, RecyclerView.ViewHolder> {
    private CategoryClickListener categoryClickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    Category category;

    public CategoryMenuAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback) {
        super(diffCallback);
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }


    @NonNull
    @Override
    public CategoryMenuAdapter.CategoryBranchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutCategoryFilterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_category_filter, parent, false);
        return new CategoryMenuAdapter.CategoryBranchDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        category = getItem(position);

//        if (position % 2 == 1) {
//            holder.itemView.setBackgroundColor(Color.parseColor("#1A00D472"));
//        }
        CategoryBranchDetailsViewHolder data = ((CategoryBranchDetailsViewHolder) holder);
        data.binding.setCategory(category);
        data.binding.executePendingBindings();
    }


    class CategoryBranchDetailsViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCategoryFilterBinding binding;

        public CategoryBranchDetailsViewHolder(final LayoutCategoryFilterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category = getItem(getAdapterPosition());
                    categoryClickListener.onFilterCategoryClicked(category);
                }
            });
        }


    }


    public interface CategoryClickListener {
        void onFilterCategoryClicked(Category category);
    }
}

package com.unicom.wasalakclientproduct.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryBinding;
import com.unicom.wasalakclientproduct.databinding.ParentRecyclerBinding;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Product;

import dagger.hilt.android.qualifiers.ActivityContext;

public class ParentAdapter extends ListAdapter<Category, ParentAdapter.CategoryViewHolder> {
    private CategoryClickListener categoryClickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private int branchId;
    private int totalBranchProducts;


    protected ParentAdapter(@NonNull DiffUtil.ItemCallback<Category> diffCallback , int branchId,int totalBranchProducts) {
        super(diffCallback);
        this.branchId = branchId;
        this.totalBranchProducts=totalBranchProducts;
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }


    @NonNull
    @Override
    public ParentAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ParentRecyclerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.parent_recycler, parent, false);
        return new ParentAdapter.CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.CategoryViewHolder holder, int position) {

        Category category = getItem(position);
        holder.binding.setCategory(category);
        holder.binding.executePendingBindings();

        LinearLayoutManager childLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        childLayoutManager.setInitialPrefetchItemCount(5);

        CategoryProductsAdapter adapter = new CategoryProductsAdapter( Product.itemCallback,categoryClickListener , branchId ,  category.getCategoryId(),totalBranchProducts);

        holder.recyclerView.setLayoutManager(childLayoutManager);

        holder.recyclerView.setRecycledViewPool(viewPool);

        holder.recyclerView.setAdapter(adapter);
        adapter.submitList(category.getProducts());


    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ParentRecyclerBinding binding;
        private RecyclerView recyclerView;


        public CategoryViewHolder(final ParentRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            recyclerView=binding.rvProducts;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryClickListener.clickCategory(getAdapterPosition());
                }
            });
        }

    }


    public interface CategoryClickListener {
        void clickCategory(int position);
        void clickProductData(Integer id , String name , int branchId , int categoryId);
        void clickAddToCart(int productId , int branchId , int categoryId , int quantity , double price);
    }
}

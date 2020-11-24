package com.unicom.wasalakclientproduct.ui.category;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryPrdouctListRowBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutCategoryProductLastBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.branch.Category;
import com.unicom.wasalakclientproduct.model.branch.Product;
import com.unicom.wasalakclientproduct.utils.Constants;

import dagger.hilt.android.qualifiers.ActivityContext;
import es.dmoral.toasty.Toasty;

public class CategoryProductsAdapter extends ListAdapter<Product, RecyclerView.ViewHolder> {
    private ParentAdapter.CategoryClickListener clickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    private int branchId, categoryId;
    private int totalBranchProducts;

    protected CategoryProductsAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback, ParentAdapter.CategoryClickListener listener, int branchId, int categoryId, int totalBranchProducts) {
        super(diffCallback);
        this.clickListener = listener;
        this.branchId = branchId;
        this.categoryId = categoryId;
        this.totalBranchProducts = totalBranchProducts;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
//        if (layoutInflater == null) {
//            layoutInflater = LayoutInflater.from(parent.getContext());
//        }
        layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                LayoutCategoryPrdouctListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_category_prdouct_list__row, parent, false);
                viewHolder = new CategoryProductsAdapter.CategoryProductsViewHolder(binding);
                break;
            case 1:
                LayoutCategoryProductLastBinding bindingLast = DataBindingUtil.inflate(layoutInflater, R.layout.layout_category_product_last, parent, false);
                viewHolder = new CategoryProductsAdapter.CategoryLastProductsViewHolder(bindingLast);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:
                Product product = getItem(position);
                CategoryProductsViewHolder defaultProductHolder = (CategoryProductsViewHolder) holder;
                defaultProductHolder.binding.setProduct(product);
                defaultProductHolder.binding.executePendingBindings();
                if (product.getProductAttachments().size() > 0)
                    GlideApp.with(defaultProductHolder.binding.getRoot().getContext())
                            .load(Constants.BASE_IAMGE_URL + product.getProductAttachments().get(0).getPath())
                            .placeholder(R.drawable.ic_wasellak_logo_color)
                            .into(defaultProductHolder.binding.ivLogo);
                break;
            case 1:
                CategoryLastProductsViewHolder lastProductHolder = (CategoryLastProductsViewHolder) holder;
                lastProductHolder.binding.executePendingBindings();
                lastProductHolder.binding.tvTotalProducts.setText(String.valueOf(totalBranchProducts));
                break;
        }
    }


    class CategoryProductsViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCategoryPrdouctListRowBinding binding;

        public CategoryProductsViewHolder(final LayoutCategoryPrdouctListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.clickProductData(getCurrentList().get(getAdapterPosition()).getId(), getCurrentList().get(getAdapterPosition()).getDisplayName(), branchId, categoryId);
                }
            });

            binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = getCurrentList().get(getAdapterPosition());
                    if (product.getQuantity() > 1)
                        clickListener.clickAddToCart(product.getId(), branchId, categoryId, 1, product.getPrice());
                    else
                        Toasty.info(context, "لا توجد هذة الكمية من المنتج", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    class CategoryLastProductsViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCategoryProductLastBinding binding;

        public CategoryLastProductsViewHolder(final LayoutCategoryProductLastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            //     binding.tvTotalProducts.setText(totalBranchProducts);
            binding.btnAllProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.clickCategory(categoryId);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return getCurrentList().size() == position ? 1 : 0;
    }
}

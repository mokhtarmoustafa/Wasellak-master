package com.unicom.wasalakclientproduct.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutProductSearchListRowBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutProductSearchListRowBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.model.store.StoreData;
import com.unicom.wasalakclientproduct.utils.Constants;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;
import es.dmoral.toasty.Toasty;

public class SearchProductsAdapter extends ListAdapter<Product, SearchProductsAdapter.CategoryViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;


    protected SearchProductsAdapter() {
        super(Product.DIFF_CALLBACK);

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutProductSearchListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_product_search_list_row, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Product model = getItem(position);
        holder.binding.setProduct(model);
        holder.binding.executePendingBindings();

        GlideApp.with(holder.binding.getRoot().getContext())
                .load(model.getProductAttachments().size() > 0 ? Constants.BASE_IAMGE_URL + model.getProductAttachments().get(0).getPath() : "")
                .placeholder(R.drawable.ic_wasellak_logo_color)
                .into(holder.binding.ivLogo);


        GlideApp.with(holder.binding.getRoot().getContext())
                .load(Constants.BASE_IAMGE_URL + model.getStoreLogo())
                .placeholder(R.drawable.ic_wasellak_logo_color)
                .into(holder.binding.ivStoreLogo);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final LayoutProductSearchListRowBinding binding;

        public CategoryViewHolder(final LayoutProductSearchListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.clickProduct(getAdapterPosition());
                }
            });

            binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = getCurrentList().get(getAdapterPosition());
                    if (product.getQuantity() > 1)
                        clickListener.clickAddToCart(product.getId(), 1, product.getPrice());
                    else
                        Toasty.info(context, R.string.error_not_enough_quanity, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public interface ClickListener {
        void clickProduct(int position);

        void clickAddToCart(int productId, int quantity, double price);
    }
}

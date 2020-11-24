package com.unicom.wasalakclientproduct.ui.order;

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
import com.unicom.wasalakclientproduct.databinding.OrderDetailsProductRowBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.model.order.OrdersDetail;
import com.unicom.wasalakclientproduct.utils.Constants;

import javax.inject.Inject;




public class ProductsAdapter extends ListAdapter<OrdersDetail, RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;

    protected ProductsAdapter() {
        super(OrdersDetail.DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        layoutInflater = LayoutInflater.from(parent.getContext());
        OrderDetailsProductRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.order_details_product_row, parent, false);
        viewHolder = new ProductsAdapter.OrderDetailsProductsViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Product product = getItem(position).getProduct();
        OrderDetailsProductsViewHolder defaultProductHolder = (OrderDetailsProductsViewHolder) holder;
        defaultProductHolder.binding.setProduct(product);
        defaultProductHolder.binding.executePendingBindings();
        if (product.getProductAttachments()!=null&&product.getProductAttachments().size() > 0)
            GlideApp.with(defaultProductHolder.binding.getRoot().getContext())
                    .load(Constants.BASE_IAMGE_URL + product.getProductAttachments().get(0).getPath())
                    .placeholder(R.drawable.ic_wasellak_logo_color)
                    .into(defaultProductHolder.binding.productImg);
    }


    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    class OrderDetailsProductsViewHolder extends RecyclerView.ViewHolder {
        private final OrderDetailsProductRowBinding binding;

        public OrderDetailsProductsViewHolder(final OrderDetailsProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }



}

package com.unicom.wasalakclientproduct.ui.cartConfirm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.ConfirmCartProductRowBinding;
import com.unicom.wasalakclientproduct.model.cart.CartModel;

import javax.inject.Inject;

public class CartProductsAdapter extends ListAdapter<CartModel.CartsDetail, CartProductsAdapter.CartViewHolder> {
    LayoutInflater layoutInflater;

    @Inject
    protected CartProductsAdapter() {
        super(CartModel.CartsDetail.DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ConfirmCartProductRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.confirm_cart_product_row, parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel.CartsDetail item = getItem(position);
        holder.binding.setProductCart(item);
        holder.binding.executePendingBindings();    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final ConfirmCartProductRowBinding binding;

        public CartViewHolder(final ConfirmCartProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}

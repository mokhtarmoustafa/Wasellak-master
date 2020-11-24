package com.unicom.wasalakclientproduct.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.adapter.QuntityProductAdpater;
import com.unicom.wasalakclientproduct.databinding.LayoutCartProductRowBinding;
import com.unicom.wasalakclientproduct.model.Quantity;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.utils.DrawableTextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductCartAdapter  extends ListAdapter<CartModel.CartsDetail , ProductCartAdapter.ProductCartViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;

    @Inject
    public ProductCartAdapter() {
        super(CartModel.CartsDetail.DIFF_CALLBACK);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LayoutCartProductRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_cart_product_row, parent, false);
        return new ProductCartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartViewHolder holder, int position) {
        CartModel.CartsDetail item = getItem(position);
        holder.binding.setProductCart(item);
        holder.binding.executePendingBindings();
        loadProductQuantity(holder.binding , item.getProduct().getQuantity().intValue() , position , item.getQuantity() , String.valueOf(item.getQuantity()));
    }

    class ProductCartViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCartProductRowBinding binding;

        public ProductCartViewHolder(final LayoutCartProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.rlQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.quantityRecycle.getVisibility() == View.GONE) {
                        binding.ivAction.setImageResource(R.drawable.ic_up);
                        binding.quantityRecycle.setVisibility(View.VISIBLE);
                        binding.lineBlack.setVisibility(View.VISIBLE);
                        binding.lineWhite.setVisibility(View.VISIBLE);
                    } else {
                        binding.ivAction.setImageResource(R.drawable.ic_down);
                        binding.quantityRecycle.setVisibility(View.GONE);
                        binding.lineBlack.setVisibility(View.GONE);
                        binding.lineWhite.setVisibility(View.GONE);
                    }
                }
            });

            binding.deleteProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.removeProduct(getItem(getAdapterPosition()).getProductId() , getAdapterPosition());
                }
            });
        }
    }

    private void loadProductQuantity(LayoutCartProductRowBinding binding , int totalQuantity , int productPosition , int productQuantity , String product_quantity) {
        if (totalQuantity > 0) {
            QuntityProductAdpater quantityAdapter = new QuntityProductAdpater(clickListener , productPosition , product_quantity);
            binding.quantityRecycle.setAdapter(quantityAdapter);
            ArrayList<Quantity> quantities = new ArrayList<>();
            for (int i = 1; i <= totalQuantity; i++) {
                if (productQuantity == i){
                    quantities.add(new Quantity(String.valueOf(i), true));
                    continue;
                }
                quantities.add(new Quantity(String.valueOf(i), false));
            }
            quantityAdapter.submitList(quantities);
            if (productQuantity > 1)
                binding.quantityRecycle.getLayoutManager().scrollToPosition(productQuantity - 1);
        }
        binding.ivAction.setImageResource(R.drawable.ic_down);
        binding.quantityRecycle.setVisibility(View.GONE);
        binding.lineBlack.setVisibility(View.GONE);
        binding.lineWhite.setVisibility(View.GONE);
    }

    public interface ClickListener {
        void clickQuantity(String quantity , int positionProduct);
        void removeProduct(int product_id , int productPosition);
    }
}

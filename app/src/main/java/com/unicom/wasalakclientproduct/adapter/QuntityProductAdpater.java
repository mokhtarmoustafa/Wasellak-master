package com.unicom.wasalakclientproduct.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutQuntityRowBinding;
import com.unicom.wasalakclientproduct.model.Quantity;
import com.unicom.wasalakclientproduct.ui.cart.ProductCartAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuntityProductAdpater extends ListAdapter<Quantity, QuntityProductAdpater.QuantityViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;
    private ProductCartAdapter.ClickListener clickListenerQuantity;
    private int productPosition;
    private String productQuantity;

    public QuntityProductAdpater() {
        super(Quantity.itemCallback);
    }

    public QuntityProductAdpater(ProductCartAdapter.ClickListener clickListenerQuantity, int productPosition , String productQuantity) {
        super(Quantity.itemCallback);
        this.clickListenerQuantity = clickListenerQuantity;
        this.productPosition = productPosition;
        this.productQuantity = productQuantity;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public QuantityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutQuntityRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_quntity_row, parent, false);
        return new QuantityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuantityViewHolder holder, int position) {
        holder.binding.setQuntity(getItem(position));
        holder.binding.executePendingBindings();
    }

    class QuantityViewHolder extends RecyclerView.ViewHolder {
        private final LayoutQuntityRowBinding binding;

        public QuantityViewHolder(final LayoutQuntityRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Quantity> quantities = getCurrentList();
                    for (int i = 0; i < quantities.size(); i++) {
                        if (i == getAdapterPosition()) {
                            quantities.get(i).setSelected(true);
                        } else {
                            quantities.get(i).setSelected(false);

                        }
                    }
                    notifyItemRangeChanged(0, quantities.size());
                    if (clickListener != null)
                        clickListener.clickMarket(getAdapterPosition());
                    if (clickListenerQuantity != null)
//                        if (getCurrentList().get(getAdapterPosition()).getNum().equalsIgnoreCase(productQuantity)){
//                            return;
//                        }
                        clickListenerQuantity.clickQuantity(getCurrentList().get(getAdapterPosition()).getNum(), productPosition);
                }
            });
        }

    }

    public void updateList(ArrayList<Quantity> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new Quantity.MyDiffUtilCallback(getCurrentList(), newList));
        diffResult.dispatchUpdatesTo(this);
    }

    public interface ClickListener {
        void clickMarket(int position);
    }
}

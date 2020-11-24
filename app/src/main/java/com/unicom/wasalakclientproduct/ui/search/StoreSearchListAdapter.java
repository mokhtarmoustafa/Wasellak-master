package com.unicom.wasalakclientproduct.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutSearchVendorListRowBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.VendorModel;
import com.unicom.wasalakclientproduct.model.search.Store;
import com.unicom.wasalakclientproduct.utils.Constants;

import javax.inject.Inject;

public class StoreSearchListAdapter extends ListAdapter<Store, StoreSearchListAdapter.VendorViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;

    @Inject
    protected StoreSearchListAdapter() {
        super(Store.DIFF_CALLBACK);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutSearchVendorListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_search_vendor_list_row, parent, false);
        return new VendorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorViewHolder holder, int position) {
        Store model = getItem(position);
        holder.binding.setModel(model);
        holder.binding.executePendingBindings();


        GlideApp.with(holder.binding.getRoot().getContext())
                .load(Constants.BASE_IAMGE_URL + model.getLogo())
                .placeholder(R.drawable.ic_wasellak_logo_color)
                .into(holder.binding.ivLogo);
    }

    class VendorViewHolder extends RecyclerView.ViewHolder {
        private final LayoutSearchVendorListRowBinding binding;

        public VendorViewHolder(final LayoutSearchVendorListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.clickVendor(getAdapterPosition());
                }
            });
        }

    }



    public interface ClickListener {
        void clickVendor(int position);
    }
}

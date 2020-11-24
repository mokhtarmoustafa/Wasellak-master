package com.unicom.wasalakclientproduct.ui.vendor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutListVendorRowBinding;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.VendorModel;
import com.unicom.wasalakclientproduct.ui.user.MyAccountFragment;
import com.unicom.wasalakclientproduct.utils.Constants;

public class VendorListAdapter extends ListAdapter<VendorModel.Result, VendorListAdapter.VendorViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;

    protected VendorListAdapter() {
        super(VendorModel.Result.DIFF_CALLBACK);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public VendorViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutListVendorRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_list_vendor_row, parent, false);
       return new VendorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorViewHolder holder, int position) {
        VendorModel.Result model = getItem(position);
        holder.binding.setModel(model);
        holder.binding.executePendingBindings();


        GlideApp.with(holder.binding.getRoot().getContext())
                .load(Constants.BASE_IAMGE_URL +model.getLogo())
                .placeholder(R.drawable.ic_wasellak_logo_color)
                .into(holder.binding.ivImage);
    }

    class VendorViewHolder extends RecyclerView.ViewHolder {
        private final LayoutListVendorRowBinding binding;

        public VendorViewHolder(final LayoutListVendorRowBinding binding) {
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

package com.unicom.wasalakclientproduct.ui.user.addresses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutListAddressRowBinding;
import com.unicom.wasalakclientproduct.model.user.address.Result;

import java.util.List;

import dagger.hilt.android.qualifiers.ActivityContext;

public class AddressAdapter extends ListAdapter<Result, AddressAdapter.AddresssViewHolder> {
    private AddressAdapter.AddressClickListener clickListener;
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;


    protected AddressAdapter(@NonNull DiffUtil.ItemCallback<Result> diffCallback) {
        super(diffCallback);
    }

    public void setClickListener(AddressAdapter.AddressClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AddressAdapter.AddresssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutListAddressRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_list_address_row, parent, false);
        return new AddressAdapter.AddresssViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.AddresssViewHolder holder, int position) {
        Result Address = getItem(position);
        holder.binding.setModel(Address);
        holder.binding.executePendingBindings();
    }

    class AddresssViewHolder extends RecyclerView.ViewHolder {
        private final LayoutListAddressRowBinding binding;

        public AddresssViewHolder(final LayoutListAddressRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result Result = getCurrentList().get(getAdapterPosition());
                    clickListener.onEditAction(getCurrentList().get(getAdapterPosition()));
                }
            });

            binding.clEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result Result = getCurrentList().get(getAdapterPosition());
                    clickListener.onEditAction(getCurrentList().get(getAdapterPosition()));
                }
            });


            binding.clRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteAction(getCurrentList().get(getAdapterPosition()));
                }
            });
        }

    }

    public void update(Result address) {

        int position = getCurrentList().indexOf(address);
        Log.d("update", "update: " + position);
        getCurrentList().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getCurrentList().size());
    }


    @Override
    public void submitList(@Nullable List<Result> list) {
        super.submitList(list);
    }

    public interface AddressClickListener {
        void onEditAction(Result Address);

        void onDeleteAction(Result Address);


    }
}

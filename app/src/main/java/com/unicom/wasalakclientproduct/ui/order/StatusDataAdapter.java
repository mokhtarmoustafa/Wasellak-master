package com.unicom.wasalakclientproduct.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.StatusDetailRowBinding;
import com.unicom.wasalakclientproduct.databinding.StatusDetailRowBinding;
import com.unicom.wasalakclientproduct.model.order.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class StatusDataAdapter extends ListAdapter<OrderStatus, RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    Context context;
    OrderStatus orderStatus;
    String deliveryDateFormat = "h:mm a";
    String t = "yyyy-MM-dd'T'HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(t, Locale.getDefault());
    SimpleDateFormat df = new SimpleDateFormat(deliveryDateFormat, Locale.getDefault());
    java.util.Date dateCreationTimeObj = null;

    public StatusDataAdapter() {
        super(OrderStatus.DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public StatusDataAdapter.OrderStatusBranchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        StatusDetailRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.status_detail_row, parent, false);
        return new StatusDataAdapter.OrderStatusBranchDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        orderStatus = getItem(position);
        OrderStatusBranchDetailsViewHolder data = ((OrderStatusBranchDetailsViewHolder) holder);
        data.binding.executePendingBindings();

        try {
            dateCreationTimeObj = sdf.parse(orderStatus.getCreationTime());
            Calendar date = Calendar.getInstance();
            date.setTime(dateCreationTimeObj);
            String dateData = df.format(dateCreationTimeObj.getTime());
            data.binding.tvStatusTime.setText(dateData);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (orderStatus.getStatus()) {
            case 2:
                data.binding.tvStatusDetail.setText(context.getString(R.string.order_status_2));
                break;
            case 4:
                data.binding.tvStatusDetail.setText(context.getString(R.string.order_status_4));
                break;
            case 7:
                data.binding.tvStatusDetail.setText(context.getString(R.string.order_status_7));
                break;
            case 11:
                data.binding.tvStatusDetail.setText(context.getString(R.string.order_status_11));
                break;
            case 13:
                data.binding.tvStatusDetail.setText(context.getString(R.string.order_status_13));
                break;
        }

    }


    class OrderStatusBranchDetailsViewHolder extends RecyclerView.ViewHolder {
        private final StatusDetailRowBinding binding;

        public OrderStatusBranchDetailsViewHolder(final StatusDetailRowBinding binding) {
            super(binding.getRoot());
            context=binding.getRoot().getContext();
            this.binding = binding;
//            binding.clParent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    category = getItem(getAdapterPosition());
//                    categoryClickListener.onFilterOrderStatusClicked(category);
//                }
//            });
        }


    }


    public interface OrderStatusClickListener {
        void onFilterOrderStatusClicked(OrderStatus category);
    }
}

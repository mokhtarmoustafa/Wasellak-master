package com.unicom.wasalakclientproduct.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.TimelineIndicatorBinding;
import com.unicom.wasalakclientproduct.model.order.OrderStatus;
import com.unicom.wasalakclientproduct.model.order.OrderStatusesHistoryForClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class OrderStatusAdapter extends ListAdapter<OrderStatusesHistoryForClient, RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    @ActivityContext
    Context context;
    String deliveryDateFormat = "dd MMM, yyyy";
    String t = "yyyy-MM-dd'T'HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(t, Locale.getDefault());
    SimpleDateFormat df = new SimpleDateFormat(deliveryDateFormat, Locale.getDefault());
    java.util.Date dateCreationTimeObj = null;
    StatusDataAdapter statusDataAdapter;

    public OrderStatusAdapter() {
        super(OrderStatusesHistoryForClient.DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public OrderStatusAdapter.OrderStatusesHistoryForClientBranchDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        TimelineIndicatorBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.timeline_indicator, parent, false);
        return new OrderStatusAdapter.OrderStatusesHistoryForClientBranchDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderStatusesHistoryForClient orderStatusesHistoryForClient = getItem(position);
        OrderStatusesHistoryForClientBranchDetailsViewHolder data = ((OrderStatusesHistoryForClientBranchDetailsViewHolder) holder);

        if (position == 0)
            data.binding.lineIndicatorTop.setVisibility(View.GONE);
        else
            data.binding.lineIndicatorTop.setVisibility(View.VISIBLE);

        if (position == getCurrentList().size()-1)
            data.binding.lineIndicatorBottom.setVisibility(View.GONE);

        data.binding.executePendingBindings();

        try {

            if (orderStatusesHistoryForClient.getStatusesDate() != null) {
                dateCreationTimeObj = sdf.parse(orderStatusesHistoryForClient.getStatusesDate());
                Calendar date = Calendar.getInstance();
                date.setTime(dateCreationTimeObj);
                String dateData = df.format(dateCreationTimeObj.getTime());
                data.binding.rowView.tvDate.setText(dateData);

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        statusDataAdapter = new StatusDataAdapter();
        statusDataAdapter.submitList(orderStatusesHistoryForClient.getOrderStatuses());
        data.binding.executePendingBindings();
        data.binding.rowView.rvStatusList.setAdapter(statusDataAdapter);

    }


    class OrderStatusesHistoryForClientBranchDetailsViewHolder extends RecyclerView.ViewHolder {
        private final TimelineIndicatorBinding binding;

        public OrderStatusesHistoryForClientBranchDetailsViewHolder(final TimelineIndicatorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }

}

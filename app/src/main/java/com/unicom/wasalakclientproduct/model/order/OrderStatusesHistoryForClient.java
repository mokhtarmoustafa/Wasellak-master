
package com.unicom.wasalakclientproduct.model.order;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class OrderStatusesHistoryForClient {

    @SerializedName("orderStatuses")
    private List<OrderStatus> mOrderStatuses;
    @SerializedName("statusesDate")
    private String mStatusesDate;

    public List<OrderStatus> getOrderStatuses() {
        return mOrderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        mOrderStatuses = orderStatuses;
    }

    public String getStatusesDate() {
        return mStatusesDate;
    }

    public void setStatusesDate(String statusesDate) {
        mStatusesDate = statusesDate;
    }


    public static DiffUtil.ItemCallback<OrderStatusesHistoryForClient> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrderStatusesHistoryForClient>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderStatusesHistoryForClient oldItem, @NonNull OrderStatusesHistoryForClient newItem) {
            return oldItem.mOrderStatuses==newItem.mOrderStatuses;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderStatusesHistoryForClient oldItem, @NonNull OrderStatusesHistoryForClient newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatusesHistoryForClient that = (OrderStatusesHistoryForClient) o;
        return mOrderStatuses.equals(that.mOrderStatuses) &&
                mStatusesDate.equals(that.mStatusesDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mOrderStatuses, mStatusesDate);
    }
}

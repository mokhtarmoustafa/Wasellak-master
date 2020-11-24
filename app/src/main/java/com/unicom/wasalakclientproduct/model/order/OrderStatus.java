
package com.unicom.wasalakclientproduct.model.order;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import javax.annotation.Generated;

public class OrderStatus {

    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private int mCreatorUserId;
    @SerializedName("id")
    private int mId;
    @SerializedName("lastModificationTime")
    private Object mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Object mLastModifierUserId;
    @SerializedName("orderId")
    private int mOrderId;
    @SerializedName("status")
    private int mStatus;

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public int getCreatorUserId() {
        return mCreatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        mCreatorUserId = creatorUserId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Object getLastModificationTime() {
        return mLastModificationTime;
    }

    public void setLastModificationTime(Object lastModificationTime) {
        mLastModificationTime = lastModificationTime;
    }

    public Object getLastModifierUserId() {
        return mLastModifierUserId;
    }

    public void setLastModifierUserId(Object lastModifierUserId) {
        mLastModifierUserId = lastModifierUserId;
    }

    public int getOrderId() {
        return mOrderId;
    }

    public void setOrderId(int orderId) {
        mOrderId = orderId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }


    public static DiffUtil.ItemCallback<OrderStatus> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrderStatus>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderStatus oldItem, @NonNull OrderStatus newItem) {
            return oldItem.mId==newItem.mId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderStatus oldItem, @NonNull OrderStatus newItem) {
            return oldItem.equals(newItem);
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return mCreationTime.equals(that.mCreationTime) &&
                mCreatorUserId==that.mCreatorUserId &&
                mId==that.mId &&
                mLastModificationTime.equals(that.mLastModificationTime) &&
                mLastModifierUserId.equals(that.mLastModifierUserId) &&
                mOrderId==that.mOrderId &&
                mStatus==that.mStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCreationTime, mCreatorUserId, mId, mLastModificationTime, mLastModifierUserId, mOrderId, mStatus);
    }

}

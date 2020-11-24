
package com.unicom.wasalakclientproduct.model.search;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class Store {

    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("id")
    private int mId;
    @SerializedName("logo")
    private String mLogo;

    public int getNearestBranchId() {
        return nearestBranchId;
    }

    public void setNearestBranchId(int nearestBranchId) {
        this.nearestBranchId = nearestBranchId;
    }

    @SerializedName("nearestBranchId")
    private int nearestBranchId;

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }


    public static DiffUtil.ItemCallback<Store> DIFF_CALLBACK = new DiffUtil.ItemCallback<Store>() {
        @Override
        public boolean areItemsTheSame(@NonNull Store oldItem, @NonNull Store newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Store oldItem, @NonNull Store newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store result = (Store) o;
        return Objects.equals(mDisplayName, mDisplayName) &&
                Objects.equals(mId, mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDisplayName, mId);
    }
}

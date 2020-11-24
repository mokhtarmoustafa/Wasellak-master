
package com.unicom.wasalakclientproduct.model.store;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.CountryClass;
import com.unicom.wasalakclientproduct.model.category.Product;
import com.unicom.wasalakclientproduct.model.user.MarketPlaceTypeModel;

public class StoreData  {

    @SerializedName("activities")
    private List<Activity> mActivities;
    @SerializedName("activityId")
    private int mActivityId;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("branchName")
    private Object mBranchName;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("filesKey")
    private Object mFilesKey;
    @SerializedName("id")
    private int mId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("products")
    private List<Product> mProducts;
    @SerializedName("vendorId")
    private Long mVendorId;

    public List<Activity> getActivities() {
        return mActivities;
    }

    public void setActivities(List<Activity> activities) {
        mActivities = activities;
    }

    public int getActivityId() {
        return mActivityId;
    }

    public void setActivityId(int activityId) {
        mActivityId = activityId;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Object getBranchName() {
        return mBranchName;
    }

    public void setBranchName(Object branchName) {
        mBranchName = branchName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public Object getFilesKey() {
        return mFilesKey;
    }

    public void setFilesKey(Object filesKey) {
        mFilesKey = filesKey;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNameAr() {
        return mNameAr;
    }

    public void setNameAr(String nameAr) {
        mNameAr = nameAr;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public Long getVendorId() {
        return mVendorId;
    }

    public void setVendorId(Long vendorId) {
        mVendorId = vendorId;
    }


    @Override
    public String toString() {
        return mDisplayName;
    }

//    @Override
//    public int compareTo(StoreData o) {
//        if(o==null)
//            return -1;
//        else
//            return this.mDisplayName.compareTo(o.mDisplayName);
//    }
}

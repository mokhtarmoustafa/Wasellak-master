
package com.unicom.wasalakclientproduct.model.branch;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;


public class Result implements Parcelable {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("categories")
    private List<Category> mCategories;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("displayDescription")
    private String displayDescription;
    @SerializedName("distance")
    private Double mDistance;
    @SerializedName("id")
    private Long mId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("name")
    private String mName;
    @SerializedName("totalBranchProducts")
    private int mTotalBranchProducts;
    @SerializedName("workingHoursFrom")
    private String mWorkingHoursFrom;
    @SerializedName("workingHoursTo")
    private String mWorkingHoursTo;
    @SerializedName("isOpenNow")
    private Boolean isOpenNow;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Double getDistance() {
        return mDistance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
 public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public void setDistance(Double distance) {
        mDistance = distance;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
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

    public int getTotalBranchProducts() {
        return mTotalBranchProducts;
    }

    public void setTotalBranchProducts(int totalBranchProducts) {
        mTotalBranchProducts = totalBranchProducts;
    }

    public String getWorkingHoursFrom() {
        return mWorkingHoursFrom;
    }

    public void setWorkingHoursFrom(String workingHoursFrom) {
        mWorkingHoursFrom = workingHoursFrom;
    }

    public String getWorkingHoursTo() {
        return mWorkingHoursTo;
    }

    public void setWorkingHoursTo(String workingHoursTo) {
        mWorkingHoursTo = workingHoursTo;
    }


    public Boolean getOpenNow() {
        return isOpenNow;
    }

    public void setOpenNow(Boolean openNow) {
        isOpenNow = openNow;
    }

    public Result() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAddress);
        dest.writeTypedList(this.mCategories);
        dest.writeString(this.mDescription);
        dest.writeValue(this.mDistance);
        dest.writeValue(this.mId);
        dest.writeString(this.mLatitude);
        dest.writeString(this.mLogo);
        dest.writeString(this.mLongitude);
        dest.writeString(this.mName);
        dest.writeInt(this.mTotalBranchProducts);
        dest.writeString(this.mWorkingHoursFrom);
        dest.writeString(this.mWorkingHoursTo);
        dest.writeValue(this.isOpenNow);
    }

    protected Result(Parcel in) {
        this.mAddress = in.readString();
        this.mCategories = in.createTypedArrayList(Category.CREATOR);
        this.mDescription = in.readString();
        this.mDistance = (Double) in.readValue(Double.class.getClassLoader());
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mLatitude = in.readString();
        this.mLogo = in.readString();
        this.mLongitude = in.readString();
        this.mName = in.readString();
        this.mTotalBranchProducts = in.readInt();
        this.mWorkingHoursFrom = in.readString();
        this.mWorkingHoursTo = in.readString();
        this.isOpenNow = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}

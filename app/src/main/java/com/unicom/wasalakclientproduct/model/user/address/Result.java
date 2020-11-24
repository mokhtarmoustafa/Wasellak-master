
package com.unicom.wasalakclientproduct.model.user.address;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;


public class Result implements Parcelable {


    @SerializedName("areaId")
    private Integer mAreaId;
    @SerializedName("areaName")
    private String mAreaName;
    @SerializedName("buildingNumber")
    private String mBuildingNumber;
    @SerializedName("description")
    private String description;
    @SerializedName("flatNumber")
    private String mFlatNumber;
    @SerializedName("floor")
    private String mFloor;
    @SerializedName("id")
    private int mId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("mapAddress")
    private String mapAddress;
    @SerializedName("name")
    private String name;
    @SerializedName("areaCityCountryId")
    private Integer countryId;
    @SerializedName("areaCityCountryName")
    private String areaCityCountryName;
    @SerializedName("areaCityId")
    private Integer cityId;
    @SerializedName("areaCityName")
    private String areaCityName;
    private int userId;


    public Result() {
    }

    public Result(String name, String description,
                  String buildingNumber, String flatNumber,
                  String floor, String mapAddress, String mLatitude, String mLongitude, Integer areaId) {


        this.name = name;
        this.description=description;
        this.mBuildingNumber = buildingNumber;
        this.mFlatNumber = flatNumber;
        this.mFloor = floor;
        this.mapAddress = mapAddress;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAreaId = areaId;


    }

    public Result(String description, String mapAddress, Integer countryId,
                  Integer cityId, Integer areaId, String mBuildingNumber, String mFlatNumber,
                  String mFloor, String mLatitude, String mLongitude) {

        this.mapAddress = mapAddress;
        this.description = description;
        this.countryId = countryId;
        this.cityId = cityId;
        this.mAreaId = areaId;
        this.mBuildingNumber = mBuildingNumber;
        this.mFlatNumber = mFlatNumber;
        this.mFloor = mFloor;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;

    }

    public Result(int mId, String name, String description,
                  String mBuildingNumber,
                  String mFloor, String mFlatNumber, String mapAddress, String mLatitude, String mLongitude, Integer mAreaId) {
        this.mAreaId = mAreaId;
        this.mBuildingNumber = mBuildingNumber;
        this.description = description;
        this.mFlatNumber = mFlatNumber;
        this.mFloor = mFloor;
        this.mId = mId;
        this.mapAddress = mapAddress;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.name = name;
    }

    public Result(int mId, String name, String description, String mBuildingNumber,
                  String mFloor, String mFlatNumber, String mapAddress, String mLatitude,
                  String mLongitude, int mAreaId) {
        this.mAreaId = mAreaId;
        this.mBuildingNumber = mBuildingNumber;
        this.description = description;
        this.mFlatNumber = mFlatNumber;
        this.mFloor = mFloor;
        this.mId = mId;
        this.mapAddress = mapAddress;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.name = name;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return mAreaId;
    }

    public void setAreaId(Integer areaId) {
        mAreaId = areaId;
    }

    public String getAreaName() {
        return mAreaName;
    }

    public void setAreaName(String areaName) {
        mAreaName = areaName;
    }

    public String getBuildingNumber() {
        return mBuildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        mBuildingNumber = buildingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlatNumber() {
        return mFlatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        mFlatNumber = flatNumber;
    }

    public String getFloor() {
        return mFloor;
    }

    public void setFloor(String floor) {
        mFloor = floor;
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

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getMapAddress() {
        return mapAddress;
    }

    public void setMapAddress(String mapAddress) {
        this.mapAddress = mapAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getmAreaId() {
        return mAreaId;
    }

    public void setmAreaId(Integer mAreaId) {
        this.mAreaId = mAreaId;
    }

    public String getmAreaName() {
        return mAreaName;
    }

    public void setmAreaName(String mAreaName) {
        this.mAreaName = mAreaName;
    }

    public void setmBuildingNumber(String mBuildingNumber) {
        this.mBuildingNumber = mBuildingNumber;
    }

    public String getmDescription() {
        return description;
    }

    public void setmDescription(String addressName) {
        this.description = addressName;
    }

    public String getmFlatNumber() {
        return mFlatNumber;
    }

    public void setmFlatNumber(String mFlatNumber) {
        this.mFlatNumber = mFlatNumber;
    }

    public String getmFloor() {
        return mFloor;
    }

    public void setmFloor(String mFloor) {
        this.mFloor = mFloor;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmMapAddress() {
        return mapAddress;
    }

    public void setmMapAddress(String addressData) {
        this.mapAddress = addressData;
    }

    public String getAreaCityCountryName() {
        return areaCityCountryName;
    }

    public void setAreaCityCountryName(String areaCityCountryName) {
        this.areaCityCountryName = areaCityCountryName;
    }

    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result that = (Result) o;
        return String.valueOf(mId).equals(that.mId);
    }


    public static DiffUtil.ItemCallback<Result> itemCallback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return String.valueOf(oldItem.getId()).equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mAreaId);
        dest.writeString(this.mAreaName);
        dest.writeString(this.mBuildingNumber);
        dest.writeString(this.description);
        dest.writeString(this.mFlatNumber);
        dest.writeString(this.mFloor);
        dest.writeInt(this.mId);
        dest.writeString(this.mLatitude);
        dest.writeString(this.mLongitude);
        dest.writeString(this.mapAddress);
        dest.writeString(this.name);
        dest.writeValue(this.countryId);
        dest.writeValue(this.cityId);
    }

    protected Result(Parcel in) {
        this.mAreaId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mAreaName = in.readString();
        this.mBuildingNumber = in.readString();
        this.description = in.readString();
        this.mFlatNumber = in.readString();
        this.mFloor = in.readString();
        this.mId = in.readInt();
        this.mLatitude = in.readString();
        this.mLongitude = in.readString();
        this.mapAddress = in.readString();
        this.name = in.readString();
        this.countryId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cityId = (Integer) in.readValue(Integer.class.getClassLoader());
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

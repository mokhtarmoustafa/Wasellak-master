
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class Store {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("marketPlaceType")
    private MarketPlaceType mMarketPlaceType;
    @SerializedName("marketPlaceTypeId")
    private Long mMarketPlaceTypeId;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("storeProducts")
    private List<StoreProduct> mStoreProducts;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public Long getCreatorUserId() {
        return mCreatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        mCreatorUserId = creatorUserId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastModificationTime() {
        return mLastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        mLastModificationTime = lastModificationTime;
    }

    public Long getLastModifierUserId() {
        return mLastModifierUserId;
    }

    public void setLastModifierUserId(Long lastModifierUserId) {
        mLastModifierUserId = lastModifierUserId;
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

    public MarketPlaceType getMarketPlaceType() {
        return mMarketPlaceType;
    }

    public void setMarketPlaceType(MarketPlaceType marketPlaceType) {
        mMarketPlaceType = marketPlaceType;
    }

    public Long getMarketPlaceTypeId() {
        return mMarketPlaceTypeId;
    }

    public void setMarketPlaceTypeId(Long marketPlaceTypeId) {
        mMarketPlaceTypeId = marketPlaceTypeId;
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

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public List<StoreProduct> getStoreProducts() {
        return mStoreProducts;
    }

    public void setStoreProducts(List<StoreProduct> storeProducts) {
        mStoreProducts = storeProducts;
    }

}

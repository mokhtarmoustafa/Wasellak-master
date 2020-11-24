
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MarketPlacesType {

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
    @SerializedName("marketPlace")
    private MarketPlace mMarketPlace;
    @SerializedName("marketPlaceId")
    private Long mMarketPlaceId;
    @SerializedName("marketPlaceTypeId")
    private Long mMarketPlaceTypeId;

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

    public MarketPlace getMarketPlace() {
        return mMarketPlace;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        mMarketPlace = marketPlace;
    }

    public Long getMarketPlaceId() {
        return mMarketPlaceId;
    }

    public void setMarketPlaceId(Long marketPlaceId) {
        mMarketPlaceId = marketPlaceId;
    }

    public Long getMarketPlaceTypeId() {
        return mMarketPlaceTypeId;
    }

    public void setMarketPlaceTypeId(Long marketPlaceTypeId) {
        mMarketPlaceTypeId = marketPlaceTypeId;
    }

}

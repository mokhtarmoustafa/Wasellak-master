
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Claim {

    @SerializedName("claimType")
    private String mClaimType;
    @SerializedName("claimValue")
    private String mClaimValue;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("tenantId")
    private Long mTenantId;
    @SerializedName("userId")
    private Long mUserId;

    public String getClaimType() {
        return mClaimType;
    }

    public void setClaimType(String claimType) {
        mClaimType = claimType;
    }

    public String getClaimValue() {
        return mClaimValue;
    }

    public void setClaimValue(String claimValue) {
        mClaimValue = claimValue;
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

    public Long getTenantId() {
        return mTenantId;
    }

    public void setTenantId(Long tenantId) {
        mTenantId = tenantId;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}

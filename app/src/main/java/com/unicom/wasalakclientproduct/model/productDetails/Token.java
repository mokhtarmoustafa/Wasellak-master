
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Token {

    @SerializedName("expireDate")
    private String mExpireDate;
    @SerializedName("id")
    private Long mId;
    @SerializedName("loginProvider")
    private String mLoginProvider;
    @SerializedName("name")
    private String mName;
    @SerializedName("tenantId")
    private Long mTenantId;
    @SerializedName("userId")
    private Long mUserId;
    @SerializedName("value")
    private String mValue;

    public String getExpireDate() {
        return mExpireDate;
    }

    public void setExpireDate(String expireDate) {
        mExpireDate = expireDate;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLoginProvider() {
        return mLoginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        mLoginProvider = loginProvider;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
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

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}

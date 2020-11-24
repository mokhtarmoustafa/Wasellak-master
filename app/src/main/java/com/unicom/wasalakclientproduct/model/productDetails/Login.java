
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Login {

    @SerializedName("id")
    private Long mId;
    @SerializedName("loginProvider")
    private String mLoginProvider;
    @SerializedName("providerKey")
    private String mProviderKey;
    @SerializedName("tenantId")
    private Long mTenantId;
    @SerializedName("userId")
    private Long mUserId;

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

    public String getProviderKey() {
        return mProviderKey;
    }

    public void setProviderKey(String providerKey) {
        mProviderKey = providerKey;
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

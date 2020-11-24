
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class StoreProduct {

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
    @SerializedName("productId")
    private Long mProductId;
    @SerializedName("productName")
    private String mProductName;
    @SerializedName("productNameAr")
    private String mProductNameAr;
    @SerializedName("store")
    private Store mStore;
    @SerializedName("storeId")
    private Long mStoreId;
    @SerializedName("storeName")
    private String mStoreName;
    @SerializedName("storeNameAr")
    private String mStoreNameAr;

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

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductNameAr() {
        return mProductNameAr;
    }

    public void setProductNameAr(String productNameAr) {
        mProductNameAr = productNameAr;
    }

    public Store getStore() {
        return mStore;
    }

    public void setStore(Store store) {
        mStore = store;
    }

    public Long getStoreId() {
        return mStoreId;
    }

    public void setStoreId(Long storeId) {
        mStoreId = storeId;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public void setStoreName(String storeName) {
        mStoreName = storeName;
    }

    public String getStoreNameAr() {
        return mStoreNameAr;
    }

    public void setStoreNameAr(String storeNameAr) {
        mStoreNameAr = storeNameAr;
    }

}

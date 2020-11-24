
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class Product {

    @SerializedName("branchProducts")
    private List<BranchProduct> mBranchProducts;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("descriptionAr")
    private String mDescriptionAr;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isActive")
    private Boolean mIsActive;
    @SerializedName("isVendorDeleted")
    private Boolean mIsVendorDeleted;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("price")
    private Long mPrice;
    @SerializedName("productAttachments")
    private List<ProductAttachment> mProductAttachments;
    @SerializedName("productCategories")
    private List<ProductCategory> mProductCategories;
    @SerializedName("productProperities")
    private List<ProductProperity> mProductProperities;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("serialNumber")
    private String mSerialNumber;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("storeProducts")
    private List<StoreProduct> mStoreProducts;
    @SerializedName("tags")
    private String mTags;

    public List<BranchProduct> getBranchProducts() {
        return mBranchProducts;
    }

    public void setBranchProducts(List<BranchProduct> branchProducts) {
        mBranchProducts = branchProducts;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDescriptionAr() {
        return mDescriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        mDescriptionAr = descriptionAr;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public Boolean getIsVendorDeleted() {
        return mIsVendorDeleted;
    }

    public void setIsVendorDeleted(Boolean isVendorDeleted) {
        mIsVendorDeleted = isVendorDeleted;
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

    public Long getPrice() {
        return mPrice;
    }

    public void setPrice(Long price) {
        mPrice = price;
    }

    public List<ProductAttachment> getProductAttachments() {
        return mProductAttachments;
    }

    public void setProductAttachments(List<ProductAttachment> productAttachments) {
        mProductAttachments = productAttachments;
    }

    public List<ProductCategory> getProductCategories() {
        return mProductCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        mProductCategories = productCategories;
    }

    public List<ProductProperity> getProductProperities() {
        return mProductProperities;
    }

    public void setProductProperities(List<ProductProperity> productProperities) {
        mProductProperities = productProperities;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        mSerialNumber = serialNumber;
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

    public String getTags() {
        return mTags;
    }

    public void setTags(String tags) {
        mTags = tags;
    }

}

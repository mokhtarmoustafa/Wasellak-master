
package com.unicom.wasalakclientproduct.model.productDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;


@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    @SerializedName("branchProducts")
    private List<BranchProduct> mBranchProducts;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("description")
    private Object mDescription;
    @SerializedName("descriptionAr")
    private Object mDescriptionAr;
    @SerializedName("displayDescription")
    private Object mDisplayDescription;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isActive")
    private Boolean mIsActive;
    @SerializedName("isVendorDeleted")
    private Boolean mIsVendorDeleted;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("price")
    private Double mPrice;
    @SerializedName("productAttachments")
    private List<ProductAttachment> mProductAttachments;
    @SerializedName("productCategories")
    private List<ProductCategory> mProductCategories;
    @SerializedName("productProperities")
    private List<ProductProperity> mProductProperities;
    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("serialNumber")
    private String mSerialNumber;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("storeProducts")
    private List<Object> mStoreProducts;
    @SerializedName("tags")
    private Object mTags;

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

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public Object getDescriptionAr() {
        return mDescriptionAr;
    }

    public void setDescriptionAr(Object descriptionAr) {
        mDescriptionAr = descriptionAr;
    }

    public Object getDisplayDescription() {
        return mDisplayDescription;
    }

    public void setDisplayDescription(Object displayDescription) {
        mDisplayDescription = displayDescription;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
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

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
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

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
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

    public List<Object> getStoreProducts() {
        return mStoreProducts;
    }

    public void setStoreProducts(List<Object> storeProducts) {
        mStoreProducts = storeProducts;
    }

    public Object getTags() {
        return mTags;
    }

    public void setTags(Object tags) {
        mTags = tags;
    }

}

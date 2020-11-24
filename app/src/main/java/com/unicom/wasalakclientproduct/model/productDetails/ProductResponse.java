
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;


public class ProductResponse {

    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("result")
    private Result mResult;
    @SerializedName("branchProducts")
    private List<BranchProduct> mBranchProducts;
    @SerializedName("creationTime")
    private String mCreationTime;

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result mResult) {
        this.mResult = mResult;
    }

//    @SerializedName("description")
//    private String mDescription;
//    @SerializedName("descriptionAr")
//    private String mDescriptionAr;
//    @SerializedName("displayDescription")
//    private String mDisplayDescription;
//    @SerializedName("displayName")
//    private String mDisplayName;
//    @SerializedName("id")
//    private Long mId;
//    @SerializedName("isActive")
//    private Boolean mIsActive;
//    @SerializedName("isVendorDeleted")
//    private Boolean mIsVendorDeleted;
//    @SerializedName("name")
//    private String mName;
//    @SerializedName("nameAr")
//    private String mNameAr;
//    @SerializedName("price")
//    private Long mPrice;
//    @SerializedName("productAttachments")
//    private List<ProductAttachment> mProductAttachments;
//    @SerializedName("productCategories")
//    private List<ProductCategory> mProductCategories;
//    @SerializedName("productProperities")
//    private List<ProductProperity> mProductProperities;
//    @SerializedName("quantity")
//    private int mQuantity;
//    @SerializedName("serialNumber")
//    private String mSerialNumber;
//    @SerializedName("status")
//    private Long mStatus;
//    @SerializedName("storeProducts")
//    private List<StoreProduct> mStoreProducts;
//    @SerializedName("tags")
//    private String mTags;

    @SerializedName("error")
    @Expose
    private ErrorNetwork error;
    @SerializedName("unAuthorizedRequest")
    private Boolean mUnAuthorizedRequest;
    @SerializedName("__abp")
    private Boolean m_Abp;
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

//    public String getDescription() {
//        return mDescription;
//    }
//
//    public void setDescription(String description) {
//        mDescription = description;
//    }
//
//    public String getDescriptionAr() {
//        return mDescriptionAr;
//    }
//
//    public void setDescriptionAr(String descriptionAr) {
//        mDescriptionAr = descriptionAr;
//    }
//
//    public String getDisplayDescription() {
//        return mDisplayDescription;
//    }
//
//    public void setDisplayDescription(String displayDescription) {
//        mDisplayDescription = displayDescription;
//    }
//
//    public String getDisplayName() {
//        return mDisplayName;
//    }
//
//    public void setDisplayName(String displayName) {
//        mDisplayName = displayName;
//    }
//
//    public Long getId() {
//        return mId;
//    }
//
//    public void setId(Long id) {
//        mId = id;
//    }
//
//    public Boolean getIsActive() {
//        return mIsActive;
//    }
//
//    public void setIsActive(Boolean isActive) {
//        mIsActive = isActive;
//    }
//
//    public Boolean getIsVendorDeleted() {
//        return mIsVendorDeleted;
//    }
//
//    public void setIsVendorDeleted(Boolean isVendorDeleted) {
//        mIsVendorDeleted = isVendorDeleted;
//    }
//
//    public String getName() {
//        return mName;
//    }
//
//    public void setName(String name) {
//        mName = name;
//    }
//
//    public String getNameAr() {
//        return mNameAr;
//    }
//
//    public void setNameAr(String nameAr) {
//        mNameAr = nameAr;
//    }
//
//    public Long getPrice() {
//        return mPrice;
//    }
//
//    public void setPrice(Long price) {
//        mPrice = price;
//    }
//
//    public List<ProductAttachment> getProductAttachments() {
//        return mProductAttachments;
//    }
//
//    public void setProductAttachments(List<ProductAttachment> productAttachments) {
//        mProductAttachments = productAttachments;
//    }
//
//    public List<ProductCategory> getProductCategories() {
//        return mProductCategories;
//    }
//
//    public void setProductCategories(List<ProductCategory> productCategories) {
//        mProductCategories = productCategories;
//    }
//
//    public List<ProductProperity> getProductProperities() {
//        return mProductProperities;
//    }
//
//    public void setProductProperities(List<ProductProperity> productProperities) {
//        mProductProperities = productProperities;
//    }
//
//    public int getQuantity() {
//        return mQuantity;
//    }
//
//    public void setQuantity(int quantity) {
//        mQuantity = quantity;
//    }
//
//    public String getSerialNumber() {
//        return mSerialNumber;
//    }
//
//    public void setSerialNumber(String serialNumber) {
//        mSerialNumber = serialNumber;
//    }
//
//    public Long getStatus() {
//        return mStatus;
//    }
//
//    public void setStatus(Long status) {
//        mStatus = status;
//    }
//
//    public List<StoreProduct> getStoreProducts() {
//        return mStoreProducts;
//    }
//
//    public void setStoreProducts(List<StoreProduct> storeProducts) {
//        mStoreProducts = storeProducts;
//    }
//
//    public String getTags() {
//        return mTags;
//    }
//
//    public void setTags(String tags) {
//        mTags = tags;
//    }

    public List<BranchProduct> getmBranchProducts() {
        return mBranchProducts;
    }

    public void setmBranchProducts(List<BranchProduct> mBranchProducts) {
        this.mBranchProducts = mBranchProducts;
    }

    public String getmCreationTime() {
        return mCreationTime;
    }

    public void setmCreationTime(String mCreationTime) {
        this.mCreationTime = mCreationTime;
    }

//    public String getmDescription() {
//        return mDescription;
//    }
//
//    public void setmDescription(String mDescription) {
//        this.mDescription = mDescription;
//    }
//
//    public String getmDescriptionAr() {
//        return mDescriptionAr;
//    }
//
//    public void setmDescriptionAr(String mDescriptionAr) {
//        this.mDescriptionAr = mDescriptionAr;
//    }
//
//    public String getmDisplayDescription() {
//        return mDisplayDescription;
//    }
//
//    public void setmDisplayDescription(String mDisplayDescription) {
//        this.mDisplayDescription = mDisplayDescription;
//    }
//
//    public String getmDisplayName() {
//        return mDisplayName;
//    }
//
//    public void setmDisplayName(String mDisplayName) {
//        this.mDisplayName = mDisplayName;
//    }
//
//    public Long getmId() {
//        return mId;
//    }
//
//    public void setmId(Long mId) {
//        this.mId = mId;
//    }
//
//    public Boolean getmIsActive() {
//        return mIsActive;
//    }
//
//    public void setmIsActive(Boolean mIsActive) {
//        this.mIsActive = mIsActive;
//    }
//
//    public Boolean getmIsVendorDeleted() {
//        return mIsVendorDeleted;
//    }
//
//    public void setmIsVendorDeleted(Boolean mIsVendorDeleted) {
//        this.mIsVendorDeleted = mIsVendorDeleted;
//    }
//
//    public String getmName() {
//        return mName;
//    }
//
//    public void setmName(String mName) {
//        this.mName = mName;
//    }
//
//    public String getmNameAr() {
//        return mNameAr;
//    }
//
//    public void setmNameAr(String mNameAr) {
//        this.mNameAr = mNameAr;
//    }
//
//    public Long getmPrice() {
//        return mPrice;
//    }
//
//    public void setmPrice(Long mPrice) {
//        this.mPrice = mPrice;
//    }
//
//    public List<ProductAttachment> getmProductAttachments() {
//        return mProductAttachments;
//    }
//
//    public void setmProductAttachments(List<ProductAttachment> mProductAttachments) {
//        this.mProductAttachments = mProductAttachments;
//    }
//
//    public List<ProductCategory> getmProductCategories() {
//        return mProductCategories;
//    }
//
//    public void setmProductCategories(List<ProductCategory> mProductCategories) {
//        this.mProductCategories = mProductCategories;
//    }
//
//    public List<ProductProperity> getmProductProperities() {
//        return mProductProperities;
//    }
//
//    public void setmProductProperities(List<ProductProperity> mProductProperities) {
//        this.mProductProperities = mProductProperities;
//    }
//
//    public int getmQuantity() {
//        return mQuantity;
//    }
//
//    public void setmQuantity(int mQuantity) {
//        this.mQuantity = mQuantity;
//    }
//
//    public String getmSerialNumber() {
//        return mSerialNumber;
//    }
//
//    public void setmSerialNumber(String mSerialNumber) {
//        this.mSerialNumber = mSerialNumber;
//    }
//
//    public Long getmStatus() {
//        return mStatus;
//    }
//
//    public void setmStatus(Long mStatus) {
//        this.mStatus = mStatus;
//    }
//
//    public List<StoreProduct> getmStoreProducts() {
//        return mStoreProducts;
//    }
//
//    public void setmStoreProducts(List<StoreProduct> mStoreProducts) {
//        this.mStoreProducts = mStoreProducts;
//    }
//
//    public String getmTags() {
//        return mTags;
//    }
//
//    public void setmTags(String mTags) {
//        this.mTags = mTags;
//    }

    public ErrorNetwork getError() {
        return error;
    }

    public void setError(ErrorNetwork error) {
        this.error = error;
    }

    public Boolean getmUnAuthorizedRequest() {
        return mUnAuthorizedRequest;
    }

    public void setmUnAuthorizedRequest(Boolean mUnAuthorizedRequest) {
        this.mUnAuthorizedRequest = mUnAuthorizedRequest;
    }

    public Boolean getM_Abp() {
        return m_Abp;
    }

    public void setM_Abp(Boolean m_Abp) {
        this.m_Abp = m_Abp;
    }

    public Boolean getmSuccess() {
        return mSuccess;
    }

    public void setmSuccess(Boolean mSuccess) {
        this.mSuccess = mSuccess;

    }


}

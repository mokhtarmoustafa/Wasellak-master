
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Category {

    @SerializedName("branchId")
    private Long mBranchId;
    @SerializedName("branches")
    private List<Branch> mBranches;
    @SerializedName("categoryId")
    private Long mCategoryId;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("descriptionAr")
    private String mDescriptionAr;
    @SerializedName("displayDescription")
    private String mDisplayDescription;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isPublished")
    private Boolean mIsPublished;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("parentCategoryId")
    private Long mParentCategoryId;
    @SerializedName("products")
    private List<Product> mProducts;

    public Long getBranchId() {
        return mBranchId;
    }

    public void setBranchId(Long branchId) {
        mBranchId = branchId;
    }

    public List<Branch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<Branch> branches) {
        mBranches = branches;
    }

    public Long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(Long categoryId) {
        mCategoryId = categoryId;
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

    public String getDisplayDescription() {
        return mDisplayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
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

    public Boolean getIsPublished() {
        return mIsPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        mIsPublished = isPublished;
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

    public Long getParentCategoryId() {
        return mParentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        mParentCategoryId = parentCategoryId;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

}

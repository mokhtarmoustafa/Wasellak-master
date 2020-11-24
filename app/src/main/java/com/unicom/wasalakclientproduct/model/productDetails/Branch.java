
package com.unicom.wasalakclientproduct.model.productDetails;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Branch {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("area")
    private Area mArea;
    @SerializedName("areaId")
    private Long mAreaId;
    @SerializedName("branchId")
    private Long mBranchId;
    @SerializedName("branchProducts")
    private List<BranchProduct> mBranchProducts;
    @SerializedName("categories")
    private List<Category> mCategories;
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
    @SerializedName("id")
    private Long mId;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("phoneNumber")
    private String mPhoneNumber;
    @SerializedName("store")
    private Store mStore;
    @SerializedName("storeId")
    private Long mStoreId;
    @SerializedName("workingHoursFrom")
    private String mWorkingHoursFrom;
    @SerializedName("workingHoursTo")
    private String mWorkingHoursTo;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Area getArea() {
        return mArea;
    }

    public void setArea(Area area) {
        mArea = area;
    }

    public Long getAreaId() {
        return mAreaId;
    }

    public void setAreaId(Long areaId) {
        mAreaId = areaId;
    }

    public Long getBranchId() {
        return mBranchId;
    }

    public void setBranchId(Long branchId) {
        mBranchId = branchId;
    }

    public List<BranchProduct> getBranchProducts() {
        return mBranchProducts;
    }

    public void setBranchProducts(List<BranchProduct> branchProducts) {
        mBranchProducts = branchProducts;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
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

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
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

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
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

    public String getWorkingHoursFrom() {
        return mWorkingHoursFrom;
    }

    public void setWorkingHoursFrom(String workingHoursFrom) {
        mWorkingHoursFrom = workingHoursFrom;
    }

    public String getWorkingHoursTo() {
        return mWorkingHoursTo;
    }

    public void setWorkingHoursTo(String workingHoursTo) {
        mWorkingHoursTo = workingHoursTo;
    }

}


package com.unicom.wasalakclientproduct.model.branch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductAttachment implements Parcelable {

    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUserId")
    private Long mCreatorUserId;
    @SerializedName("extention")
    private String mExtention;
    @SerializedName("id")
    private Long mId;
    @SerializedName("isMainImage")
    private Boolean mIsMainImage;
    @SerializedName("lastModificationTime")
    private String mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Long mLastModifierUserId;
    @SerializedName("name")
    private String mName;
    @SerializedName("path")
    private String mPath;
    @SerializedName("product")
    private Product mProduct;
    @SerializedName("productId")
    private Long mProductId;

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

    public String getExtention() {
        return mExtention;
    }

    public void setExtention(String extention) {
        mExtention = extention;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getIsMainImage() {
        return mIsMainImage;
    }

    public void setIsMainImage(Boolean isMainImage) {
        mIsMainImage = isMainImage;
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

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCreationTime);
        dest.writeValue(this.mCreatorUserId);
        dest.writeString(this.mExtention);
        dest.writeValue(this.mId);
        dest.writeValue(this.mIsMainImage);
        dest.writeString(this.mLastModificationTime);
        dest.writeValue(this.mLastModifierUserId);
        dest.writeString(this.mName);
        dest.writeString(this.mPath);
        dest.writeParcelable(this.mProduct, flags);
        dest.writeValue(this.mProductId);
    }

    public ProductAttachment() {
    }

    protected ProductAttachment(Parcel in) {
        this.mCreationTime = in.readString();
        this.mCreatorUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mExtention = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mIsMainImage = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mLastModificationTime = in.readString();
        this.mLastModifierUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mPath = in.readString();
        this.mProduct = in.readParcelable(Product.class.getClassLoader());
        this.mProductId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<ProductAttachment> CREATOR = new Creator<ProductAttachment>() {
        @Override
        public ProductAttachment createFromParcel(Parcel source) {
            return new ProductAttachment(source);
        }

        @Override
        public ProductAttachment[] newArray(int size) {
            return new ProductAttachment[size];
        }
    };
}

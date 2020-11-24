package com.unicom.wasalakclientproduct.model.category;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.model.GlideApp;
import com.unicom.wasalakclientproduct.model.VendorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Parcelable {

    @SerializedName("categoryId")
    private int mCategoryId;
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
    private int mId;
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
    private double mPrice;
    @SerializedName("priceWithDiscount")
    private double priceWithDiscount;
    @SerializedName("product")
    private Product mProduct;
    @SerializedName("productAttachments")
    private List<ProductAttachment> mProductAttachments;
    @SerializedName("productCategories")
    private List<ProductCategory> mProductCategories;
    @SerializedName("productId")
    private int mProductId;
    @SerializedName("productProperities")
    private List<ProductProperity> mProductProperities;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("serialNumber")
    private String mSerialNumber;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("tags")
    private String mTags;
    @SerializedName("storeLogo")
    private String storeLogo;

    private boolean isQuntityVisible = true;

    public static DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.mId == newItem.mId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    public boolean isQuntityVisible() {
        return isQuntityVisible;
    }

    public void setQuntityVisible(boolean quntityVisible) {
        isQuntityVisible = quntityVisible;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public List<ProductAttachment> getProductAttachments() {
        return mProductAttachments!=null&&mProductAttachments.size()>0?mProductAttachments:null;
    }

    public void setProductAttachments(List<ProductAttachment> productAttachments) {
        mProductAttachments = productAttachments;
    }

    public List<ProductCategory> getProductCategories() {
        return mProductCategories!=null&&mProductCategories.size()>0?mProductCategories:null;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        mProductCategories = productCategories;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
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


    public String getTags() {
        return mTags;
    }

    public void setTags(String tags) {
        mTags = tags;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public Product() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product result = (Product) o;
        return Objects.equals(mName, result.mName) &&
                Objects.equals(mId, result.mId) &&
                Objects.equals(mNameAr, result.mNameAr) &&
                Objects.equals(mDescription, result.mDescription) &&
                Objects.equals(mIsActive, result.mIsActive) &&
                Objects.equals(isQuntityVisible, result.isQuntityVisible)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mCategoryId);
        dest.writeString(this.mCreationTime);
        dest.writeValue(this.mCreatorUserId);
        dest.writeString(this.mDescription);
        dest.writeString(this.mDescriptionAr);
        dest.writeString(this.mDisplayDescription);
        dest.writeString(this.mDisplayName);
        dest.writeInt(this.mId);
        dest.writeValue(this.mIsActive);
        dest.writeValue(this.mIsVendorDeleted);
        dest.writeString(this.mLastModificationTime);
        dest.writeValue(this.mLastModifierUserId);
        dest.writeString(this.mName);
        dest.writeString(this.mNameAr);
        dest.writeDouble(this.mPrice);
        dest.writeDouble(this.priceWithDiscount);
        dest.writeParcelable(this.mProduct, flags);
        dest.writeTypedList(this.mProductAttachments);
        dest.writeList(this.mProductCategories);
        dest.writeInt(this.mProductId);
        dest.writeList(this.mProductProperities);
        dest.writeValue(this.mQuantity);
        dest.writeString(this.mSerialNumber);
        dest.writeValue(this.mStatus);
        dest.writeString(this.mTags);
        dest.writeString(this.storeLogo);
        dest.writeByte(this.isQuntityVisible ? (byte) 1 : (byte) 0);
    }

    protected Product(Parcel in) {
        this.mCategoryId = in.readInt();
        this.mCreationTime = in.readString();
        this.mCreatorUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mDescription = in.readString();
        this.mDescriptionAr = in.readString();
        this.mDisplayDescription = in.readString();
        this.mDisplayName = in.readString();
        this.mId = in.readInt();
        this.mIsActive = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mIsVendorDeleted = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mLastModificationTime = in.readString();
        this.mLastModifierUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mNameAr = in.readString();
        this.mPrice = in.readDouble();
        this.priceWithDiscount = in.readDouble();
        this.mProduct = in.readParcelable(Product.class.getClassLoader());
        this.mProductAttachments = in.createTypedArrayList(ProductAttachment.CREATOR);
        this.mProductCategories = new ArrayList<ProductCategory>();
        in.readList(this.mProductCategories, ProductCategory.class.getClassLoader());
        this.mProductId = in.readInt();
        this.mProductProperities = new ArrayList<ProductProperity>();
        in.readList(this.mProductProperities, ProductProperity.class.getClassLoader());
        this.mQuantity = (Long) in.readValue(Long.class.getClassLoader());
        this.mSerialNumber = in.readString();
        this.mStatus = (Long) in.readValue(Long.class.getClassLoader());
        this.mTags = in.readString();
        this.storeLogo = in.readString();
        this.isQuntityVisible = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}


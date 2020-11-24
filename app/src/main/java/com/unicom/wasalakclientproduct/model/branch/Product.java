
package com.unicom.wasalakclientproduct.model.branch;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Product implements Parcelable {

    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("descriptionAr")
    private String mDescriptionAr;
    @SerializedName("displayDescription")
    private String mDisplayDescription;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("id")
    private int id;
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
    @SerializedName("quantity")
    private Double mQuantity;
    @SerializedName("serialNumber")
    private String mSerialNumber;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("tags")
    private String mTags;

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public Object getDescription() {
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
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
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

    public Product() {
    }

    @Override
    public String toString() {
        return "InnerDatum{" +
                "id=" + id +
                ", name='" + mDisplayName + '\'' +
                ", description='" + mDisplayDescription + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
      Product that = (Product) o;
        return String.valueOf(id).equals(id) &&
                mDisplayName.equals(that.mDisplayName) &&
                mDisplayDescription.equals(that.mDisplayDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDisplayName, mDisplayDescription);
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return String.valueOf(oldItem.getId()).equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCreationTime);
        dest.writeString(this.mDescription);
        dest.writeString(this.mDescriptionAr);
        dest.writeString(this.mDisplayDescription);
        dest.writeString(this.mDisplayName);
        dest.writeInt(this.id);
        dest.writeValue(this.mIsActive);
        dest.writeValue(this.mIsVendorDeleted);
        dest.writeString(this.mName);
        dest.writeString(this.mNameAr);
        dest.writeValue(this.mPrice);
        dest.writeTypedList(this.mProductAttachments);
        dest.writeValue(this.mQuantity);
        dest.writeString(this.mSerialNumber);
        dest.writeValue(this.mStatus);
        dest.writeString(this.mTags);
    }

    protected Product(Parcel in) {
        this.mCreationTime = in.readString();
        this.mDescription = in.readString();
        this.mDescriptionAr = in.readString();
        this.mDisplayDescription = in.readString();
        this.mDisplayName = in.readString();
        this.id = in.readInt();
        this.mIsActive = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mIsVendorDeleted = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mName = in.readString();
        this.mNameAr = in.readString();
        this.mPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.mProductAttachments = in.createTypedArrayList(ProductAttachment.CREATOR);
        this.mQuantity = (Double) in.readValue(Double.class.getClassLoader());
        this.mSerialNumber = in.readString();
        this.mStatus = (Long) in.readValue(Long.class.getClassLoader());
        this.mTags = in.readString();
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

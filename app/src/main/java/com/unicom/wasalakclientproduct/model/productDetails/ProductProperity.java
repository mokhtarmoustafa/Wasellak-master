
package com.unicom.wasalakclientproduct.model.productDetails;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductProperity implements Parcelable {

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
    @SerializedName("property")
    private Property mProperty;
    @SerializedName("propertyId")
    private Long mPropertyId;
    @SerializedName("propertyValue")
    private PropertyValue mPropertyValue;
    @SerializedName("propertyValueId")
    private Long mPropertyValueId;

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

    public Property getProperty() {
        return mProperty;
    }

    public void setProperty(Property property) {
        mProperty = property;
    }

    public Long getPropertyId() {
        return mPropertyId;
    }

    public void setPropertyId(Long propertyId) {
        mPropertyId = propertyId;
    }

    public PropertyValue getPropertyValue() {
        return mPropertyValue;
    }

    public void setPropertyValue(PropertyValue propertyValue) {
        mPropertyValue = propertyValue;
    }

    public Long getPropertyValueId() {
        return mPropertyValueId;
    }

    public void setPropertyValueId(Long propertyValueId) {
        mPropertyValueId = propertyValueId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCreationTime);
        dest.writeValue(this.mCreatorUserId);
        dest.writeValue(this.mId);
        dest.writeString(this.mLastModificationTime);
        dest.writeValue(this.mLastModifierUserId);
        dest.writeValue(this.mProductId);
        dest.writeParcelable(this.mProperty, flags);
        dest.writeValue(this.mPropertyId);
        dest.writeParcelable(this.mPropertyValue, flags);
        dest.writeValue(this.mPropertyValueId);
    }

    public ProductProperity() {
    }

    public ProductProperity(String mCreationTime, Long mCreatorUserId, Long mId, String mLastModificationTime, Long mLastModifierUserId, Long mProductId, Property mProperty, Long mPropertyId, PropertyValue mPropertyValue, Long mPropertyValueId) {
        this.mCreationTime = mCreationTime;
        this.mCreatorUserId = mCreatorUserId;
        this.mId = mId;
        this.mLastModificationTime = mLastModificationTime;
        this.mLastModifierUserId = mLastModifierUserId;
        this.mProductId = mProductId;
        this.mProperty = mProperty;
        this.mPropertyId = mPropertyId;
        this.mPropertyValue = mPropertyValue;
        this.mPropertyValueId = mPropertyValueId;
    }

    public ProductProperity(Long mId, Property mProperty, PropertyValue mPropertyValue) {
        this.mId = mId;
        this.mProperty = mProperty;
        this.mPropertyValue = mPropertyValue;
    }

    protected ProductProperity(Parcel in) {
        this.mCreationTime = in.readString();
        this.mCreatorUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mLastModificationTime = in.readString();
        this.mLastModifierUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mProductId = (Long) in.readValue(Long.class.getClassLoader());
        this.mProperty = in.readParcelable(Property.class.getClassLoader());
        this.mPropertyId = (Long) in.readValue(Long.class.getClassLoader());
        this.mPropertyValue = in.readParcelable(PropertyValue.class.getClassLoader());
        this.mPropertyValueId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductProperity> CREATOR = new Parcelable.Creator<ProductProperity>() {
        @Override
        public ProductProperity createFromParcel(Parcel source) {
            return new ProductProperity(source);
        }

        @Override
        public ProductProperity[] newArray(int size) {
            return new ProductProperity[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductProperity that = (ProductProperity) o;
        return mId.equals(that.getId()) ;
    }
    public static DiffUtil.ItemCallback<ProductProperity> itemCallback = new DiffUtil.ItemCallback<ProductProperity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProductProperity oldItem, @NonNull ProductProperity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductProperity oldItem, @NonNull ProductProperity newItem) {
            return oldItem.equals(newItem);
        }
    };
}

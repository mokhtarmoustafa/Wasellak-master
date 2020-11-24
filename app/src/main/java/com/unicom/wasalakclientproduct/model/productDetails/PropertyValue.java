
package com.unicom.wasalakclientproduct.model.productDetails;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PropertyValue implements Parcelable {

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
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("propertyId")
    private Long mPropertyId;

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

    public Long getPropertyId() {
        return mPropertyId;
    }

    public void setPropertyId(Long propertyId) {
        mPropertyId = propertyId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCreationTime);
        dest.writeValue(this.mCreatorUserId);
        dest.writeString(this.mDescription);
        dest.writeString(this.mDescriptionAr);
        dest.writeValue(this.mId);
        dest.writeString(this.mLastModificationTime);
        dest.writeValue(this.mLastModifierUserId);
        dest.writeString(this.mName);
        dest.writeString(this.mNameAr);
        dest.writeValue(this.mPropertyId);
    }

    public PropertyValue() {
    }

    public PropertyValue(Long mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    protected PropertyValue(Parcel in) {
        this.mCreationTime = in.readString();
        this.mCreatorUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mDescription = in.readString();
        this.mDescriptionAr = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mLastModificationTime = in.readString();
        this.mLastModifierUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mNameAr = in.readString();
        this.mPropertyId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<PropertyValue> CREATOR = new Parcelable.Creator<PropertyValue>() {
        @Override
        public PropertyValue createFromParcel(Parcel source) {
            return new PropertyValue(source);
        }

        @Override
        public PropertyValue[] newArray(int size) {
            return new PropertyValue[size];
        }
    };
}

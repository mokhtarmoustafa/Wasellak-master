
package com.unicom.wasalakclientproduct.model.branch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Property implements Parcelable {

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
    @SerializedName("propertyValues")
    private List<PropertyValue> mPropertyValues;

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

    public List<PropertyValue> getPropertyValues() {
        return mPropertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        mPropertyValues = propertyValues;
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
        dest.writeList(this.mPropertyValues);
    }

    public Property() {
    }

    protected Property(Parcel in) {
        this.mCreationTime = in.readString();
        this.mCreatorUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mDescription = in.readString();
        this.mDescriptionAr = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mLastModificationTime = in.readString();
        this.mLastModifierUserId = (Long) in.readValue(Long.class.getClassLoader());
        this.mName = in.readString();
        this.mNameAr = in.readString();
        this.mPropertyValues = new ArrayList<PropertyValue>();
        in.readList(this.mPropertyValues, PropertyValue.class.getClassLoader());
    }

    public static final Parcelable.Creator<Property> CREATOR = new Parcelable.Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel source) {
            return new Property(source);
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };
}

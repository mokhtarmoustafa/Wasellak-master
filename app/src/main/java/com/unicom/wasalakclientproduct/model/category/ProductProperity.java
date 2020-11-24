
package com.unicom.wasalakclientproduct.model.category;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductProperity {

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

}

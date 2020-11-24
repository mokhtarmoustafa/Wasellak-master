
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CourierVehicles {

    @SerializedName("capacity")
    private Long mCapacity;
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
    @SerializedName("userId")
    private Long mUserId;
    @SerializedName("vehicleModel")
    private VehicleModel mVehicleModel;
    @SerializedName("vehicleModelId")
    private Long mVehicleModelId;
    @SerializedName("vehicleType")
    private VehicleType mVehicleType;
    @SerializedName("vehicleTypeId")
    private Long mVehicleTypeId;
    @SerializedName("year")
    private String mYear;

    public Long getCapacity() {
        return mCapacity;
    }

    public void setCapacity(Long capacity) {
        mCapacity = capacity;
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

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public VehicleModel getVehicleModel() {
        return mVehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        mVehicleModel = vehicleModel;
    }

    public Long getVehicleModelId() {
        return mVehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        mVehicleModelId = vehicleModelId;
    }

    public VehicleType getVehicleType() {
        return mVehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        mVehicleType = vehicleType;
    }

    public Long getVehicleTypeId() {
        return mVehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        mVehicleTypeId = vehicleTypeId;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

}

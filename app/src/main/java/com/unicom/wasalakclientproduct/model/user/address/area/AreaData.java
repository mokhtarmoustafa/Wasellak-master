
package com.unicom.wasalakclientproduct.model.user.address.area;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AreaData {

    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("id")
    private int mId;

    public AreaData( int mId,String mDisplayName) {
        this.mDisplayName = mDisplayName;
        this.mId = mId;
    }

//    public Long getCityId() {
//        return mCityId;
//    }
//
//    public void setCityId(Long cityId) {
//        mCityId = cityId;
//    }

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



    @Override
    public String toString() {
        return mDisplayName;
    }
}

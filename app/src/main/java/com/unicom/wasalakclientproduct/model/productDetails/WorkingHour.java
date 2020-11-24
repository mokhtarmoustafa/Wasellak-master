
package com.unicom.wasalakclientproduct.model.productDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class WorkingHour {

    @SerializedName("date")
    private String mDate;
    @SerializedName("dayName")
    private String mDayName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("timeFrom")
    private String mTimeFrom;
    @SerializedName("timeTo")
    private String mTimeTo;
    @SerializedName("userId")
    private Long mUserId;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDayName() {
        return mDayName;
    }

    public void setDayName(String dayName) {
        mDayName = dayName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTimeFrom() {
        return mTimeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        mTimeFrom = timeFrom;
    }

    public String getTimeTo() {
        return mTimeTo;
    }

    public void setTimeTo(String timeTo) {
        mTimeTo = timeTo;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

}

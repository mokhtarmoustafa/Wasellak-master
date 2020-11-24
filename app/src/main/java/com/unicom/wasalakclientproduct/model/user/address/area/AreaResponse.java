
package com.unicom.wasalakclientproduct.model.user.address.area;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;

public class AreaResponse implements Parcelable {

    @SerializedName("error")
    private ErrorNetwork mError;
    @SerializedName("result")
    private List<AreaData> mResult;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("targetUrl")
    private Object mTargetUrl;
    @SerializedName("unAuthorizedRequest")
    private Boolean mUnAuthorizedRequest;
    @SerializedName("__abp")
    private Boolean m_Abp;

    public ErrorNetwork getError() {
        return mError;
    }

    public void setError(ErrorNetwork error) {
        mError = error;
    }

    public List<AreaData> getResult() {
        return mResult;
    }

    public void setResult(List<AreaData> result) {
        mResult = result;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

    public Object getTargetUrl() {
        return mTargetUrl;
    }

    public void setTargetUrl(Object targetUrl) {
        mTargetUrl = targetUrl;
    }

    public Boolean getUnAuthorizedRequest() {
        return mUnAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(Boolean unAuthorizedRequest) {
        mUnAuthorizedRequest = unAuthorizedRequest;
    }

    public Boolean get_Abp() {
        return m_Abp;
    }

    public void set_Abp(Boolean _Abp) {
        m_Abp = _Abp;
    }


    public ErrorNetwork getmError() {
        return mError;
    }

    public void setmError(ErrorNetwork mError) {
        this.mError = mError;
    }

    public List<AreaData> getmResult() {
        return mResult;
    }

    public void setmResult(List<AreaData> mResult) {
        this.mResult = mResult;
    }

    public Boolean getmSuccess() {
        return mSuccess;
    }

    public void setmSuccess(Boolean mSuccess) {
        this.mSuccess = mSuccess;
    }

    public Object getmTargetUrl() {
        return mTargetUrl;
    }

    public void setmTargetUrl(Object mTargetUrl) {
        this.mTargetUrl = mTargetUrl;
    }

    public Boolean getmUnAuthorizedRequest() {
        return mUnAuthorizedRequest;
    }

    public void setmUnAuthorizedRequest(Boolean mUnAuthorizedRequest) {
        this.mUnAuthorizedRequest = mUnAuthorizedRequest;
    }

    public Boolean getM_Abp() {
        return m_Abp;
    }

    public void setM_Abp(Boolean m_Abp) {
        this.m_Abp = m_Abp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.mError, flags);
        dest.writeList(this.mResult);
        dest.writeValue(this.mSuccess);
        dest.writeParcelable((Parcelable) this.mTargetUrl, flags);
        dest.writeValue(this.mUnAuthorizedRequest);
        dest.writeValue(this.m_Abp);
    }

    public AreaResponse() {
    }

    protected AreaResponse(Parcel in) {
        this.mError = in.readParcelable(ErrorNetwork.class.getClassLoader());
        this.mResult = new ArrayList<AreaData>();
        in.readList(this.mResult, AreaData.class.getClassLoader());
        this.mSuccess = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mTargetUrl = in.readParcelable(Object.class.getClassLoader());
        this.mUnAuthorizedRequest = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.m_Abp = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AreaResponse> CREATOR = new Parcelable.Creator<AreaResponse>() {
        @Override
        public AreaResponse createFromParcel(Parcel source) {
            return new AreaResponse(source);
        }

        @Override
        public AreaResponse[] newArray(int size) {
            return new AreaResponse[size];
        }
    };
}

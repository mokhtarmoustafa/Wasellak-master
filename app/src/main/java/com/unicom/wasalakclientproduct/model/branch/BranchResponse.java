
package com.unicom.wasalakclientproduct.model.branch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;

public class BranchResponse implements Parcelable {

    @SerializedName("result")
    private Result mResult;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("targetUrl")
    private Object mTargetUrl;
    @SerializedName("error")
    @Expose
    private ErrorNetwork error;
    @SerializedName("unAuthorizedRequest")
    private Boolean mUnAuthorizedRequest;
    @SerializedName("__abp")
    private Boolean m_Abp;


    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
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


//    public Result getmResult() {
//        return mResult;
//    }

    public void setmResult(Result mResult) {
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

    public void setError(ErrorNetwork error) {
        this.error = error;
    }

    public ErrorNetwork getError() {
        return error;
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

    public static Creator<BranchResponse> getCREATOR() {
        return CREATOR;
    }

    public Boolean get_Abp() {
        return m_Abp;
    }

    public void set_Abp(Boolean _Abp) {
        m_Abp = _Abp;
    }

    public BranchResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.error, flags);
        dest.writeParcelable(this.mResult, flags);
        dest.writeValue(this.mSuccess);
        dest.writeParcelable((Parcelable) this.mTargetUrl, flags);
        dest.writeValue(this.mUnAuthorizedRequest);
        dest.writeValue(this.m_Abp);
    }

    protected BranchResponse(Parcel in) {
        this.error = in.readParcelable(Object.class.getClassLoader());
        this.mResult = in.readParcelable(Result.class.getClassLoader());
        this.mSuccess = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mTargetUrl = in.readParcelable(Object.class.getClassLoader());
        this.error = in.readParcelable(ErrorNetwork.class.getClassLoader());
        this.mUnAuthorizedRequest = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.m_Abp = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<BranchResponse> CREATOR = new Creator<BranchResponse>() {
        @Override
        public BranchResponse createFromParcel(Parcel source) {
            return new BranchResponse(source);
        }

        @Override
        public BranchResponse[] newArray(int size) {
            return new BranchResponse[size];
        }
    };
}

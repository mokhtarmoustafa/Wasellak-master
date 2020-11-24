package com.unicom.wasalakclientproduct.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;

public class CartOperationsResponse {
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

    public ErrorNetwork getError() {
        return error;
    }

    public void setError(ErrorNetwork error) {
        this.error = error;
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
}


package com.unicom.wasalakclientproduct.model.user.address;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;


import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AddressResponse {

    @SerializedName("error")
    private ErrorNetwork mError;
    @SerializedName("result")
    private List<Result> mResult;
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

    public List<Result> getResult() {
        return mResult;
    }

    public void setResult(List<Result> result) {
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

}

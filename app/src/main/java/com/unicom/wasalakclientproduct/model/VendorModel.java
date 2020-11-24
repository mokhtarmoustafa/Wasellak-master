package com.unicom.wasalakclientproduct.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class VendorModel {

    @SerializedName("result")
    @Expose
    private List<VendorModel.Result> result;
    @SerializedName("targetUrl")
    @Expose
    private Object targetUrl;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private ErrorNetwork error;
    @SerializedName("unAuthorizedRequest")
    @Expose
    private Boolean unAuthorizedRequest;
    @SerializedName("__abp")
    @Expose
    private Boolean abp;

    public List<VendorModel.Result> getResult() {
        return result;
    }

    public void setResult(List<VendorModel.Result> result) {
        this.result = result;
    }

    public Object getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(Object targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ErrorNetwork getError() {
        return error;
    }

    public void setError(ErrorNetwork error) {
        this.error = error;
    }

    public Boolean getUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(Boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    public Boolean getAbp() {
        return abp;
    }

    public void setAbp(Boolean abp) {
        this.abp = abp;
    }

    public static class Result {
     @SerializedName("id")
     @Expose
     int id;
        @SerializedName("name")
        @Expose
        String  name;
        @SerializedName("logo")
        @Expose
        String logo;
        @SerializedName("nameAR")
        @Expose
        String nameAr;
        @SerializedName("distance")
        @Expose
        Double  distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getNameAr() {
            return nameAr;
        }

        public void setNameAr(String nameAr) {
            this.nameAr = nameAr;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }


        public static DiffUtil.ItemCallback<VendorModel.Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<VendorModel.Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull VendorModel.Result oldItem, @NonNull VendorModel.Result newItem) {
            return oldItem.id==newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull VendorModel.Result oldItem, @NonNull VendorModel.Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorModel.Result result = (VendorModel.Result) o;
        return Objects.equals(name, result.name) &&
                Objects.equals(id, result.id)&&
                Objects.equals(nameAr, result.nameAr)&&
                 Objects.equals(distance, result.distance)&&
                Objects.equals(logo, result.logo)

                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }



    }


}

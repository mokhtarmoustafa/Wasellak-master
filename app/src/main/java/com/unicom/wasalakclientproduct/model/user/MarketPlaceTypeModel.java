package com.unicom.wasalakclientproduct.model.user;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;

import java.util.List;
import java.util.Objects;

public class MarketPlaceTypeModel {
    @SerializedName("result")
    @Expose
    private Result result;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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

    public class Result {

        @SerializedName("totalCount")
        @Expose
        private Integer totalCount;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

    }

    public static class Item {

        @SerializedName("nameAr")
        @Expose
        private String nameAr;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("descriptionAr")
        @Expose
        private Object descriptionAr;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("displayDescription")
        @Expose
        private Object displayDescription;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getNameAr() {
            return nameAr;
        }

        public void setNameAr(String nameAr) {
            this.nameAr = nameAr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Object getDescriptionAr() {
            return descriptionAr;
        }

        public void setDescriptionAr(Object descriptionAr) {
            this.descriptionAr = descriptionAr;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getDisplayDescription() {
            return displayDescription;
        }

        public void setDisplayDescription(Object displayDescription) {
            this.displayDescription = displayDescription;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(nameAr, item.nameAr) &&
                    Objects.equals(name, item.name) &&
                    Objects.equals(displayName, item.displayName) &&
                    Objects.equals(descriptionAr, item.descriptionAr) &&
                    Objects.equals(description, item.description) &&
                    Objects.equals(displayDescription, item.displayDescription) &&
                    id.equals(item.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nameAr, name, displayName, descriptionAr, description, displayDescription, id);
        }
        public static DiffUtil.ItemCallback<Item> itemCallback = new DiffUtil.ItemCallback<Item>() {
            @Override
            public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
                return oldItem.equals(newItem);
            }
        };

    }
}

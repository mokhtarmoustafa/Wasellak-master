package com.unicom.wasalakclientproduct.ui.orderCreate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderCreateModel {
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
    private Object error;
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

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
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

        @SerializedName("vendorId")
        @Expose
        private Integer vendorId;
        @SerializedName("assignedToId")
        @Expose
        private Object assignedToId;
        @SerializedName("assignedTo")
        @Expose
        private Object assignedTo;
        @SerializedName("creationDate")
        @Expose
        private String creationDate;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("totalPrice")
        @Expose
        private Double totalPrice;
        @SerializedName("paymentMethod")
        @Expose
        private Integer paymentMethod;
        @SerializedName("totalWeight")
        @Expose
        private Double totalWeight;
        @SerializedName("totalCapacity")
        @Expose
        private Double totalCapacity;
        @SerializedName("newOrdersTotalCount")
        @Expose
        private Integer newOrdersTotalCount;
        @SerializedName("ordersTotalCount")
        @Expose
        private Integer ordersTotalCount;
        @SerializedName("notes")
        @Expose
        private String notes;
        @SerializedName("courierAvailability")
        @Expose
        private Integer courierAvailability;
        @SerializedName("ordersDetails")
        @Expose
        private List<OrdersDetail> ordersDetails = null;
        @SerializedName("clientAddress")
        @Expose
        private Object clientAddress;
        @SerializedName("branchId")
        @Expose
        private Integer branchId;
        @SerializedName("branch")
        @Expose
        private Branch branch;
        @SerializedName("clientAddressId")
        @Expose
        private Object clientAddressId;
        @SerializedName("creatorUser")
        @Expose
        private Object creatorUser;
        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getVendorId() {
            return vendorId;
        }

        public void setVendorId(Integer vendorId) {
            this.vendorId = vendorId;
        }

        public Object getAssignedToId() {
            return assignedToId;
        }

        public void setAssignedToId(Object assignedToId) {
            this.assignedToId = assignedToId;
        }

        public Object getAssignedTo() {
            return assignedTo;
        }

        public void setAssignedTo(Object assignedTo) {
            this.assignedTo = assignedTo;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Integer getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(Integer paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public Double getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(Double totalWeight) {
            this.totalWeight = totalWeight;
        }

        public Double getTotalCapacity() {
            return totalCapacity;
        }

        public void setTotalCapacity(Double totalCapacity) {
            this.totalCapacity = totalCapacity;
        }

        public Integer getNewOrdersTotalCount() {
            return newOrdersTotalCount;
        }

        public void setNewOrdersTotalCount(Integer newOrdersTotalCount) {
            this.newOrdersTotalCount = newOrdersTotalCount;
        }

        public Integer getOrdersTotalCount() {
            return ordersTotalCount;
        }

        public void setOrdersTotalCount(Integer ordersTotalCount) {
            this.ordersTotalCount = ordersTotalCount;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Integer getCourierAvailability() {
            return courierAvailability;
        }

        public void setCourierAvailability(Integer courierAvailability) {
            this.courierAvailability = courierAvailability;
        }

        public List<OrdersDetail> getOrdersDetails() {
            return ordersDetails;
        }

        public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
            this.ordersDetails = ordersDetails;
        }

        public Object getClientAddress() {
            return clientAddress;
        }

        public void setClientAddress(Object clientAddress) {
            this.clientAddress = clientAddress;
        }

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public Branch getBranch() {
            return branch;
        }

        public void setBranch(Branch branch) {
            this.branch = branch;
        }

        public Object getClientAddressId() {
            return clientAddressId;
        }

        public void setClientAddressId(Object clientAddressId) {
            this.clientAddressId = clientAddressId;
        }

        public Object getCreatorUser() {
            return creatorUser;
        }

        public void setCreatorUser(Object creatorUser) {
            this.creatorUser = creatorUser;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class OrdersDetail {

        @SerializedName("orderId")
        @Expose
        private Integer orderId;
        @SerializedName("productId")
        @Expose
        private Integer productId;
        @SerializedName("product")
        @Expose
        private Object product;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;
        @SerializedName("price")
        @Expose
            private Double price;
        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Object getProduct() {
            return product;
        }

        public void setProduct(Object product) {
            this.product = product;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }


    public class Branch {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("nameAr")
        @Expose
        private String nameAr;
        @SerializedName("displayName")
        @Expose
        private Object displayName;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("storeId")
        @Expose
        private Integer storeId;
        @SerializedName("storeName")
        @Expose
        private Object storeName;
        @SerializedName("areaId")
        @Expose
        private Integer areaId;
        @SerializedName("areaName")
        @Expose
        private Object areaName;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("logo")
        @Expose
        private Object logo;
        @SerializedName("descriptionAr")
        @Expose
        private Object descriptionAr;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("workingHours")
        @Expose
        private List<Object> workingHours = null;
        @SerializedName("branchProducts")
        @Expose
        private List<Object> branchProducts = null;
        @SerializedName("area")
        @Expose
        private Object area;
        @SerializedName("store")
        @Expose
        private Object store;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameAr() {
            return nameAr;
        }

        public void setNameAr(String nameAr) {
            this.nameAr = nameAr;
        }

        public Object getDisplayName() {
            return displayName;
        }

        public void setDisplayName(Object displayName) {
            this.displayName = displayName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Integer getStoreId() {
            return storeId;
        }

        public void setStoreId(Integer storeId) {
            this.storeId = storeId;
        }

        public Object getStoreName() {
            return storeName;
        }

        public void setStoreName(Object storeName) {
            this.storeName = storeName;
        }

        public Integer getAreaId() {
            return areaId;
        }

        public void setAreaId(Integer areaId) {
            this.areaId = areaId;
        }

        public Object getAreaName() {
            return areaName;
        }

        public void setAreaName(Object areaName) {
            this.areaName = areaName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
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

        public List<Object> getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(List<Object> workingHours) {
            this.workingHours = workingHours;
        }

        public List<Object> getBranchProducts() {
            return branchProducts;
        }

        public void setBranchProducts(List<Object> branchProducts) {
            this.branchProducts = branchProducts;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public Object getStore() {
            return store;
        }

        public void setStore(Object store) {
            this.store = store;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}

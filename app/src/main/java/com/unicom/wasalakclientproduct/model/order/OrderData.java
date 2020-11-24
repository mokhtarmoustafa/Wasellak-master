
package com.unicom.wasalakclientproduct.model.order;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.productDetails.Branch;


public class OrderData {

    @SerializedName("assignedTo")
    private Object mAssignedTo;
    @SerializedName("assignedToId")
    private int mAssignedToId;
    @SerializedName("branch")
    private Branch mBranch;
    @SerializedName("branchId")
    private int mBranchId;
    @SerializedName("clientAddress")
    private CartModel.Addresses mClientAddress;
    @SerializedName("clientAddressId")
    private int mClientAddressId;
    @SerializedName("clientStatusId")
    private int mClientStatusId;
    @SerializedName("courierAvailability")
    private Long mCourierAvailability;
    @SerializedName("creationDate")
    private String mCreationDate;
    @SerializedName("creationTime")
    private String mCreationTime;
    @SerializedName("creatorUser")
    private CreatorUser mCreatorUser;
    @SerializedName("creatorUserId")
    private int mCreatorUserId;
    @SerializedName("deliveryDatetime")
    private String mDeliveryDatetime;
    @SerializedName("id")
    private int mId;
    @SerializedName("lastModificationTime")
    private Object mLastModificationTime;
    @SerializedName("lastModifierUserId")
    private Object mLastModifierUserId;
    @SerializedName("newOrdersTotalCount")
    private int mNewOrdersTotalCount;
    @SerializedName("notes")
    private String mNotes;
    @SerializedName("orderStatuses")
    private List<OrderStatus> mOrderStatuses;
    @SerializedName("ordersDetails")
    private List<OrdersDetail> mOrdersDetails;
    @SerializedName("ordersTotalCount")
    private int mOrdersTotalCount;
    @SerializedName("paymentMethod")
    private int mPaymentMethod;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("totalCapacity")
    private Long mTotalCapacity;
    @SerializedName("totalPrice")
    private Double mTotalPrice;
    @SerializedName("totalWeight")
    private Long mTotalWeight;
    @SerializedName("vendorId")
    private int mVendorId;
    @SerializedName("deliveryDuration")
    private String deliveryDuration;
    @SerializedName("orderStatusesHistoryForClient")
    private List<OrderStatusesHistoryForClient> mOrderStatusesHistoryForClient;

    public Object getAssignedTo() {
        return mAssignedTo;
    }

    public void setAssignedTo(Object assignedTo) {
        mAssignedTo = assignedTo;
    }

    public int getAssignedToId() {
        return mAssignedToId;
    }

    public void setAssignedToId(int assignedToId) {
        mAssignedToId = assignedToId;
    }

    public Branch getBranch() {
        return mBranch;
    }

    public void setBranch(Branch branch) {
        mBranch = branch;
    }

    public int getBranchId() {
        return mBranchId;
    }

    public void setBranchId(int branchId) {
        mBranchId = branchId;
    }

    public CartModel.Addresses getClientAddress() {
        return mClientAddress;
    }

    public void setClientAddress(CartModel.Addresses clientAddress) {
        mClientAddress = clientAddress;
    }

    public int getClientAddressId() {
        return mClientAddressId;
    }

    public void setClientAddressId(int clientAddressId) {
        mClientAddressId = clientAddressId;
    }

    public int getClientStatusId() {
        return mClientStatusId;
    }

    public void setClientStatusId(int clientStatusId) {
        mClientStatusId = clientStatusId;
    }

    public Long getCourierAvailability() {
        return mCourierAvailability;
    }

    public void setCourierAvailability(Long courierAvailability) {
        mCourierAvailability = courierAvailability;
    }

    public String getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(String creationDate) {
        mCreationDate = creationDate;
    }

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public CreatorUser getCreatorUser() {
        return mCreatorUser;
    }

    public void setCreatorUser(CreatorUser creatorUser) {
        mCreatorUser = creatorUser;
    }

    public int getCreatorUserId() {
        return mCreatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        mCreatorUserId = creatorUserId;
    }

    public String getDeliveryDatetime() {
        return mDeliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        mDeliveryDatetime = deliveryDatetime;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Object getLastModificationTime() {
        return mLastModificationTime;
    }

    public void setLastModificationTime(Object lastModificationTime) {
        mLastModificationTime = lastModificationTime;
    }

    public Object getLastModifierUserId() {
        return mLastModifierUserId;
    }

    public void setLastModifierUserId(Object lastModifierUserId) {
        mLastModifierUserId = lastModifierUserId;
    }

    public int getNewOrdersTotalCount() {
        return mNewOrdersTotalCount;
    }

    public void setNewOrdersTotalCount(int newOrdersTotalCount) {
        mNewOrdersTotalCount = newOrdersTotalCount;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public List<OrderStatus> getOrderStatuses() {
        return mOrderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        mOrderStatuses = orderStatuses;
    }

    public List<OrdersDetail> getOrdersDetails() {
        return mOrdersDetails;
    }

    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
        mOrdersDetails = ordersDetails;
    }

    public int getOrdersTotalCount() {
        return mOrdersTotalCount;
    }

    public void setOrdersTotalCount(int ordersTotalCount) {
        mOrdersTotalCount = ordersTotalCount;
    }

    public int getPaymentMethod() {
        return mPaymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        mPaymentMethod = paymentMethod;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public Long getTotalCapacity() {
        return mTotalCapacity;
    }

    public void setTotalCapacity(Long totalCapacity) {
        mTotalCapacity = totalCapacity;
    }

    public Double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        mTotalPrice = totalPrice;
    }

    public Long getTotalWeight() {
        return mTotalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        mTotalWeight = totalWeight;
    }

    public int getVendorId() {
        return mVendorId;
    }

    public void setVendorId(int vendorId) {
        mVendorId = vendorId;
    }

    public String getDeliveryDuration() {
        return deliveryDuration;
    }

    public void setDeliveryDuration(String deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
    }

    public List<OrderStatusesHistoryForClient> getOrderStatusesHistoryForClient() {
        return mOrderStatusesHistoryForClient;
    }

    public void setOrderStatusesHistoryForClient(List<OrderStatusesHistoryForClient> orderStatusesHistoryForClient) {
        mOrderStatusesHistoryForClient = orderStatusesHistoryForClient;
    }
}

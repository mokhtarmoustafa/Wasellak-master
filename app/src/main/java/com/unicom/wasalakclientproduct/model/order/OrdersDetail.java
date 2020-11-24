
package com.unicom.wasalakclientproduct.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.cart.CartModel;
import com.unicom.wasalakclientproduct.model.category.Product;

import java.util.Objects;


public class OrdersDetail {

    @SerializedName("category")
    private Object mCategory;
    @SerializedName("categoryId")
    private Object mCategoryId;
    @SerializedName("id")
    private int mId;
    @SerializedName("notes")
    private Object mNotes;
    @SerializedName("orderId")
    private int mOrderId;
    @SerializedName("price")
    private Double mPrice;
    @SerializedName("product")
    private Product mProduct;
    @SerializedName("productId")
    private int mProductId;
    @SerializedName("quantity")
    private int mQuantity;

    public Object getCategory() {
        return mCategory;
    }

    public void setCategory(Object category) {
        mCategory = category;
    }

    public Object getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(Object categoryId) {
        mCategoryId = categoryId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Object getNotes() {
        return mNotes;
    }

    public void setNotes(Object notes) {
        mNotes = notes;
    }

    public int getOrderId() {
        return mOrderId;
    }

    public void setOrderId(int orderId) {
        mOrderId = orderId;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCategory, mCategoryId, mId, mNotes, mOrderId, mPrice, mProduct, mProductId, mQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersDetail that = (OrdersDetail) o;
        return mId == that.mId &&
                mOrderId == that.mOrderId &&
                mProductId == that.mProductId &&
                mQuantity == that.mQuantity &&
                Objects.equals(mCategory, that.mCategory) &&
                Objects.equals(mCategoryId, that.mCategoryId) &&
                Objects.equals(mNotes, that.mNotes) &&
                Objects.equals(mPrice, that.mPrice) &&
                Objects.equals(mProduct, that.mProduct);
    }

    public static DiffUtil.ItemCallback<OrdersDetail> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrdersDetail>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrdersDetail oldItem, @NonNull OrdersDetail newItem) {
            return oldItem.mId==newItem.mId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrdersDetail oldItem, @NonNull OrdersDetail newItem) {
            return oldItem.equals(newItem);
        }
    };

}

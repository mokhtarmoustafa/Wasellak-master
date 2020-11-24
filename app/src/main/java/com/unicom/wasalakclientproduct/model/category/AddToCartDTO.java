package com.unicom.wasalakclientproduct.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCartDTO {
    @SerializedName("cartId")
    @Expose
    private Integer cartId;
    @SerializedName("branchId")
    @Expose
    private Integer branchId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Double price;

    public AddToCartDTO(Integer branchId, Integer productId, Integer categoryId, Integer quantity, Double price) {
        this.branchId = branchId;
        this.productId = productId;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

}

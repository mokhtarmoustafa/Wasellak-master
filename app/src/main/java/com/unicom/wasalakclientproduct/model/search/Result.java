
package com.unicom.wasalakclientproduct.model.search;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.category.Product;

public class Result {

    @SerializedName("products")
    private List<Product> mProducts;
    @SerializedName("stores")
    private List<Store> mStores;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public List<Store> getStores() {
        return mStores;
    }

    public void setStores(List<Store> stores) {
        mStores = stores;
    }

}

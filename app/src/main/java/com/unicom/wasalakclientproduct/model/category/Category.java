
package com.unicom.wasalakclientproduct.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.gson.annotations.SerializedName;


public class Category implements Parcelable {

    @SerializedName("displayDescription")
    private String mDisplayDescription;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("productCount")
    private Long mProductCount;
    @SerializedName("products")
    private List<Product> mProducts;

    public String getDisplayDescription() {
        return mDisplayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        mDisplayDescription = displayDescription;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public Long getProductCount() {
        return mProductCount;
    }

    public void setProductCount(Long productCount) {
        mProductCount = productCount;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDisplayDescription);
        dest.writeString(this.mDisplayName);
        dest.writeValue(this.mProductCount);
        dest.writeList(this.mProducts);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.mDisplayDescription = in.readString();
        this.mDisplayName = in.readString();
        this.mProductCount = (Long) in.readValue(Long.class.getClassLoader());
        this.mProducts = new ArrayList<Product>();
        in.readList(this.mProducts, Product.class.getClassLoader());
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public String toString() {
        return "InnerDatum{" +
                " name='" + mDisplayName + '\'' +
                ", description='" + mDisplayDescription + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return mDisplayName.equals(that.mDisplayName) &&
                mDisplayDescription.equals(that.mDisplayDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDisplayName, mDisplayDescription);
    }

    public static DiffUtil.ItemCallback<Category> itemCallback = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getDisplayName().equals(newItem.getDisplayName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.equals(newItem);
        }
    };

}

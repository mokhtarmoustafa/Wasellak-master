package com.unicom.wasalakclientproduct.model.cart;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.model.ErrorNetwork;
import com.unicom.wasalakclientproduct.model.GlideApp;

import java.util.List;
import java.util.Objects;

public class CartModel {
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

    public static class Result {

        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("branchId")
        @Expose
        private Integer branchId;
        @SerializedName("branchName")
        @Expose
        private String branchName;
        @SerializedName("branchLogo")
        @Expose
        private String branchLogo;
        @SerializedName("totalPrice")
        @Expose
        private Double totalPrice;
        @SerializedName("totalProduct")
        @Expose
        private Integer totalProduct;
        @SerializedName("cartsDetails")
        @Expose
        private List<CartsDetail> cartsDetails = null;
        @SerializedName("clientAddresses")
        @Expose
        private List<Addresses> addresses;
        @SerializedName("deliveryAddress")
        @Expose
        private Addresses address;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchLogo() {
            return branchLogo;
        }

        public void setBranchLogo(String branchLogo) {
            this.branchLogo = branchLogo;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Integer getTotalProduct() {
            return totalProduct;
        }

        public void setTotalProduct(Integer totalProduct) {
            this.totalProduct = totalProduct;
        }

        public List<CartsDetail> getCartsDetails() {
            return cartsDetails;
        }

        public void setCartsDetails(List<CartsDetail> cartsDetails) {
            this.cartsDetails = cartsDetails;
        }

        public List<Addresses> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Addresses> addresses) {
            this.addresses = addresses;
        }

        public Addresses getAddress() {
            return address;
        }

        public void setAddress(Addresses address) {
            this.address = address;
        }

        @BindingAdapter("android:loadBranchLogo")
        public static void loadImageLogo(ImageView imageView, String branchLogo) {
            GlideApp.with(imageView)
                    .load("http://eg-unicom.dyndns.org:4100/api" + branchLogo)
                    .placeholder(R.drawable.ic_wasellak_logo_color)
                    .into(imageView);
        }

    }

    public static class CartsDetail {

        @SerializedName("cartId")
        @Expose
        private Integer cartId;
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
        @SerializedName("product")
        @Expose
        private Product product;
        @SerializedName("id")
        @Expose
        private Integer id;

        public CartsDetail(Integer cartId, Integer productId, Integer categoryId, Integer quantity, Double price, Product product, Integer id) {
            this.cartId = cartId;
            this.productId = productId;
            this.categoryId = categoryId;
            this.quantity = quantity;
            this.price = price;
            this.product = product;
            this.id = id;
        }

        public Integer getCartId() {
            return cartId;
        }

        public void setCartId(Integer cartId) {
            this.cartId = cartId;
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

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
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
            CartsDetail that = (CartsDetail) o;
            return Objects.equals(cartId, that.cartId) &&
                    Objects.equals(productId, that.productId) &&
                    Objects.equals(categoryId, that.categoryId) &&
                    Objects.equals(quantity, that.quantity) &&
                    Objects.equals(price, that.price) &&
                    Objects.equals(product, that.product) &&
                    Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cartId, productId, categoryId, quantity, price, product, id);
        }

        public static DiffUtil.ItemCallback<CartsDetail> DIFF_CALLBACK = new DiffUtil.ItemCallback<CartsDetail>() {
            @Override
            public boolean areItemsTheSame(@NonNull CartsDetail oldItem, @NonNull CartsDetail newItem) {
                return oldItem.id==newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull CartsDetail oldItem, @NonNull CartsDetail newItem) {
                return oldItem.equals(newItem);
            }
        };
        @BindingAdapter("android:loadProductImage")
        public static void loadImage(ImageView imageView, String productImage) {
            GlideApp.with(imageView)
                    .load("http://eg-unicom.dyndns.org:4100/api" + productImage)
                    .placeholder(R.drawable.ic_wasellak_logo_color)
                    .into(imageView);
        }
    }

    public class Product {

        @SerializedName("nameAr")
        @Expose
        private String nameAr;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("descriptionAr")
        @Expose
        private String descriptionAr;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("quantity")
        @Expose
        private Double quantity;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("overView")
        @Expose
        private Object overView;
        @SerializedName("serialNumber")
        @Expose
        private String serialNumber;
        @SerializedName("productCount")
        @Expose
        private Object productCount;
        @SerializedName("productAttachments")
        @Expose
        private List<ProductAttachment> productAttachments = null;
        @SerializedName("productCategories")
        @Expose
        private List<ProductCategory> productCategories = null;
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

        public String getDescriptionAr() {
            return descriptionAr;
        }

        public void setDescriptionAr(String descriptionAr) {
            this.descriptionAr = descriptionAr;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Object getOverView() {
            return overView;
        }

        public void setOverView(Object overView) {
            this.overView = overView;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }

        public Object getProductCount() {
            return productCount;
        }

        public void setProductCount(Object productCount) {
            this.productCount = productCount;
        }

        public List<ProductAttachment> getProductAttachments() {
            return productAttachments;
        }

        public void setProductAttachments(List<ProductAttachment> productAttachments) {
            this.productAttachments = productAttachments;
        }

        public List<ProductCategory> getProductCategories() {
            return productCategories;
        }

        public void setProductCategories(List<ProductCategory> productCategories) {
            this.productCategories = productCategories;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }




    }

    public class ProductAttachment {

        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("extention")
        @Expose
        private String extention;
        @SerializedName("productId")
        @Expose
        private Integer productId;
        @SerializedName("isMainImage")
        @Expose
        private Object isMainImage;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExtention() {
            return extention;
        }

        public void setExtention(String extention) {
            this.extention = extention;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Object getIsMainImage() {
            return isMainImage;
        }

        public void setIsMainImage(Object isMainImage) {
            this.isMainImage = isMainImage;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class ProductCategory {

        @SerializedName("category")
        @Expose
        private Category category;
        @SerializedName("id")
        @Expose
        private Integer id;

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class Category {

        @SerializedName("nameAr")
        @Expose
        private String nameAr;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("descriptionAr")
        @Expose
        private String descriptionAr;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("isPublished")
        @Expose
        private Boolean isPublished;
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

        public String getDescriptionAr() {
            return descriptionAr;
        }

        public void setDescriptionAr(String descriptionAr) {
            this.descriptionAr = descriptionAr;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getIsPublished() {
            return isPublished;
        }

        public void setIsPublished(Boolean isPublished) {
            this.isPublished = isPublished;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class Addresses {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("buildingNumber")
        @Expose
        private Integer buildingNumber;
        @SerializedName("floor")
        @Expose
        private String floor;
        @SerializedName("flatNumber")
        @Expose
        private Integer flatNumber;
        @SerializedName("mapAddress")
        @Expose
        private String mapAddress;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("areaId")
        @Expose
        private Integer areaId;
        @SerializedName("areaName")
        @Expose
        private Object areaName;
        @SerializedName("areaCityId")
        @Expose
        private Object areaCityId;
        @SerializedName("areaCityName")
        @Expose
        private Object areaCityName;
        @SerializedName("areaCityCountryId")
        @Expose
        private Object areaCityCountryId;
        @SerializedName("areaCityCountryName")
        @Expose
        private Object areaCityCountryName;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getBuildingNumber() {
            return buildingNumber;
        }

        public void setBuildingNumber(Integer buildingNumber) {
            this.buildingNumber = buildingNumber;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public Integer getFlatNumber() {
            return flatNumber;
        }

        public void setFlatNumber(Integer flatNumber) {
            this.flatNumber = flatNumber;
        }

        public String getMapAddress() {
            return mapAddress;
        }

        public void setMapAddress(String mapAddress) {
            this.mapAddress = mapAddress;
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

        public Object getAreaCityId() {
            return areaCityId;
        }

        public void setAreaCityId(Object areaCityId) {
            this.areaCityId = areaCityId;
        }

        public Object getAreaCityName() {
            return areaCityName;
        }

        public void setAreaCityName(Object areaCityName) {
            this.areaCityName = areaCityName;
        }

        public Object getAreaCityCountryId() {
            return areaCityCountryId;
        }

        public void setAreaCityCountryId(Object areaCityCountryId) {
            this.areaCityCountryId = areaCityCountryId;
        }

        public Object getAreaCityCountryName() {
            return areaCityCountryName;
        }

        public void setAreaCityCountryName(Object areaCityCountryName) {
            this.areaCityCountryName = areaCityCountryName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}

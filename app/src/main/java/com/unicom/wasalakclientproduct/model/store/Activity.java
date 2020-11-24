
package com.unicom.wasalakclientproduct.model.store;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import com.unicom.wasalakclientproduct.model.CountryClass;

public class Activity  {

    @SerializedName("activity")
    private Activity mActivity;
    @SerializedName("activityId")
    private Long mActivityId;
    @SerializedName("categories")
    private List<Object> mCategories;
    @SerializedName("description")
    private Object mDescription;
    @SerializedName("descriptionAr")
    private Object mDescriptionAr;
    @SerializedName("displayDescription")
    private Object mDisplayDescription;
    @SerializedName("displayName")
    private String mDisplayName;
    @SerializedName("filesKey")
    private Object mFilesKey;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("nameAr")
    private String mNameAr;
    @SerializedName("storeId")
    private Long mStoreId;

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    public Long getActivityId() {
        return mActivityId;
    }

    public void setActivityId(Long activityId) {
        mActivityId = activityId;
    }

    public List<Object> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Object> categories) {
        mCategories = categories;
    }

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public Object getDescriptionAr() {
        return mDescriptionAr;
    }

    public void setDescriptionAr(Object descriptionAr) {
        mDescriptionAr = descriptionAr;
    }

    public Object getDisplayDescription() {
        return mDisplayDescription;
    }

    public void setDisplayDescription(Object displayDescription) {
        mDisplayDescription = displayDescription;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public Object getFilesKey() {
        return mFilesKey;
    }

    public void setFilesKey(Object filesKey) {
        mFilesKey = filesKey;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNameAr() {
        return mNameAr;
    }

    public void setNameAr(String nameAr) {
        mNameAr = nameAr;
    }

    public Long getStoreId() {
        return mStoreId;
    }

    public void setStoreId(Long storeId) {
        mStoreId = storeId;
    }

//    @Override
//    public int compareTo(Activity o) {
//        if (o == null)
//            return -1;
//        else
//            return this.mDisplayName.compareTo(o.mDisplayName);
//    }

    @Override
    public String toString() {
       return  getActivity().getDisplayName();
    }
}

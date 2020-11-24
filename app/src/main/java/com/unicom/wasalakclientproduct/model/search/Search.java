package com.unicom.wasalakclientproduct.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class Search implements Parcelable {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Search() {
    }

    public Search(String searchName) {
        this.name=searchName;
    }

    protected Search(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Search that = (Search) o;
        return String.valueOf(name).equals(that.name);
    }

    public static DiffUtil.ItemCallback<Search> itemCallback = new DiffUtil.ItemCallback<Search>() {
        @Override
        public boolean areItemsTheSame(@NonNull Search oldItem, @NonNull Search newItem) {
            return String.valueOf(oldItem.getName()).equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Search oldItem, @NonNull Search newItem) {
            return oldItem.equals(newItem);
        }
    };

  
}

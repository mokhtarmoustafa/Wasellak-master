package com.unicom.wasalakclientproduct.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quantity {
    private String num="1";
    boolean isSelected;

    public Quantity(String num, boolean isSelected) {
        this.num = num;
        this.isSelected = isSelected;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Quantity item = (Quantity) o;
            return Objects.equals(num, item.num) &&
                    Objects.equals(isSelected, item.isSelected) ;
        }

    @Override
    public int hashCode() {
        return Objects.hash(num,isSelected);
    }


    public static DiffUtil.ItemCallback<Quantity> itemCallback = new DiffUtil.ItemCallback<Quantity>() {
        @Override
        public boolean areItemsTheSame(@NonNull Quantity oldItem, @NonNull Quantity newItem) {
            return oldItem.num.equals(newItem.getNum());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Quantity oldItem, @NonNull Quantity newItem) {
            return oldItem.equals(newItem);
        }
    };


    public static class MyDiffUtilCallback extends DiffUtil.Callback {
        List<Quantity> newList;
        List<Quantity> oldList;

        public MyDiffUtilCallback() {
        }

        public MyDiffUtilCallback(List<Quantity> newList, List<Quantity> oldList) {
            this.newList = newList;
            this.oldList = oldList;
        }

        @Override
        public int getOldListSize() {
            return oldList != null ? oldList.size() : 0 ;
        }

        @Override
        public int getNewListSize() {
            return newList != null ? newList.size() : 0 ;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).num.equals(newList.get(newItemPosition).getNum());        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));

        }


        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            Quantity newQuentity = newList.get(newItemPosition);
            Quantity oldQuantity = oldList.get(oldItemPosition);

            Bundle diff = new Bundle();
            if(!newQuentity.getNum().equals(oldQuantity.getNum())){
                diff.putString("name", newQuentity.getNum());
            }
            if(!newQuentity.isSelected()==oldQuantity.isSelected()){
                diff.putBoolean("phone", newQuentity.isSelected());
            }
            if (diff.size()==0){
                return null;
            }
            return diff;
        }
    }

}

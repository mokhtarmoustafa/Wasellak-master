package com.unicom.wasalakclientproduct.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Comment {
    private  String id;
    private String name;


    public static DiffUtil.ItemCallback<Comment> DIFF_CALLBACK = new DiffUtil.ItemCallback<Comment>() {
        @Override
        public boolean areItemsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.id==newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Comment oldItem, @NonNull Comment newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment result = (Comment) o;
        return Objects.equals(name, result.name) &&
                Objects.equals(id, result.id)

                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

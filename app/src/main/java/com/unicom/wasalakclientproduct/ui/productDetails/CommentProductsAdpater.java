package com.unicom.wasalakclientproduct.ui.productDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutCommentDetailListRowBinding;
import com.unicom.wasalakclientproduct.model.Comment;
import com.unicom.wasalakclientproduct.ui.product_list.ProductVendorListAdapter;

public class CommentProductsAdpater extends ListAdapter<Comment, CommentProductsAdpater.CommentViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;

    protected CommentProductsAdpater() {
        super(Comment.DIFF_CALLBACK);
        ;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        LayoutCommentDetailListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_comment_detail_list_row, parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private final LayoutCommentDetailListRowBinding binding;

        public CommentViewHolder(final LayoutCommentDetailListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener!=null) clickListener.clickMarket(getAdapterPosition());
                }
            });
        }

    }

    public interface ClickListener {
        void clickMarket(int position);
    }
}

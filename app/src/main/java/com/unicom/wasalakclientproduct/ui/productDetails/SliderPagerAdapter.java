package com.unicom.wasalakclientproduct.ui.productDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutImageSliderRowBinding;

class SliderPagerAdapter extends RecyclerView.Adapter<SliderPagerAdapter.ImageSliderViewHolder> {

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutImageSliderRowBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.layout_image_slider_row,
                        parent,
                        false);

        return new ImageSliderViewHolder( binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        public ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
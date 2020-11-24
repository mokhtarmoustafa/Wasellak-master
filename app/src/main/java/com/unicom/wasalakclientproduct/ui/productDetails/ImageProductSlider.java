package com.unicom.wasalakclientproduct.ui.productDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.adapter.SliderAdapter;
import com.unicom.wasalakclientproduct.databinding.LayoutImageSliderRowBinding;

import java.util.ArrayList;
import java.util.List;

public class ImageProductSlider  extends
        SliderViewAdapter<ImageProductSlider.ImagSliderVH> {

    private Context context;
    LayoutInflater layoutInflater;
    private List<String> imageUrls = new ArrayList<>();


    public ImageProductSlider(Context context) {
        this.context = context;
    }

    public void renewItems(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }


    @Override
    public ImagSliderVH onCreateViewHolder(ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }

        LayoutImageSliderRowBinding binding = DataBindingUtil.inflate(layoutInflater,  R.layout.layout_image_slider_row, parent, false);
        return new ImagSliderVH(binding);
    }

    @Override
    public void onBindViewHolder(ImagSliderVH viewHolder, final int position) {
        String url = imageUrls.get(position);
        viewHolder.binding.setImageUrl(url);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return imageUrls.size();
    }

    class ImagSliderVH extends SliderViewAdapter.ViewHolder{
        private final LayoutImageSliderRowBinding binding;



        public ImagSliderVH(LayoutImageSliderRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

package com.unicom.wasalakclientproduct.utils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.model.GlideApp;

import dagger.hilt.android.scopes.FragmentScoped;


public class GlideBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void showImage(ImageView view, String url) {
        if ( url != null&&!url.isEmpty()) {
            GlideApp.with(view.getContext())
                    .load("http://eg-unicom.dyndns.org:4100/api" + url)
                    .placeholder(R.drawable.ic_wasellak_logo_color)
                    .into(view);
        }else {
            view.setImageResource(R.drawable.ic_wasellak_logo_color);

        }
    }
    @BindingAdapter("selectedShape")
    public  static void changeTetViewShape(TextView textView,boolean isSelected){
        float radius = textView.getContext().getResources().getDimension(R.dimen.dimen_6dp);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, radius)
                .build();
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        if (isSelected) {
            shapeDrawable.setStroke(4.0f, ContextCompat.getColor(textView.getContext(),R.color.colorAccent));
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.black));
            shapeDrawable.setFillColor(ContextCompat.getColorStateList(textView.getContext(),R.color.white));

        }else {
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.colorSecondaryText));
            shapeDrawable.setStroke(2.0f, ContextCompat.getColor(textView.getContext(),R.color.colorSecondaryText));
            shapeDrawable.setFillColor(ContextCompat.getColorStateList(textView.getContext(),R.color.white));

        }
        ViewCompat.setBackground(textView, shapeDrawable);

    }
}

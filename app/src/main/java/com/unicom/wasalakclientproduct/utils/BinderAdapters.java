package com.unicom.wasalakclientproduct.utils;

import android.graphics.Paint;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import static android.graphics.Paint.STRIKE_THRU_TEXT_FLAG;

public class BinderAdapters {
    @BindingAdapter("strikethrough")
   public static void strikethrough(TextView view, boolean show) {
        view.setPaintFlags (show?view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG:view.getPaintFlags()&Paint.STRIKE_THRU_TEXT_FLAG) ;
    }
}

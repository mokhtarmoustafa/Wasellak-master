package com.unicom.wasalakclientproduct.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutRatingbarBinding;
import com.unicom.wasalakclientproduct.databinding.SeekbarLayoutBinding;

public class RatingbarWithText extends LinearLayout {
    private  String value;
    private LayoutRatingbarBinding binding;
    SeekBar seekBar;

    public RatingbarWithText(Context context) {
        super(context);
        iniViewa(context);
    }

    public RatingbarWithText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.RatingbarWithText);
        value = typedArray
                .getString(R.styleable.RatingbarWithText_rateval);
        typedArray.recycle();
        iniViewa(context);
    }

    public RatingbarWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.RatingbarWithText);
        value = typedArray
                .getString(R.styleable.RatingbarWithText_rateval);
        typedArray.recycle();
    iniViewa(context);
    }
    private  void iniViewa(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      binding=  DataBindingUtil.inflate(inflater,R.layout.layout_ratingbar, this,true);



    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        binding.seekbarvalue.setText(value!=null?value:"");
        binding.rate2.setProgress(20);
        seekBar=binding.rate2;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }
}

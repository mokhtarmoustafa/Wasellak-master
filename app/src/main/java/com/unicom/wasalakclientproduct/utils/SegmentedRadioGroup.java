package com.unicom.wasalakclientproduct.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.unicom.wasalakclientproduct.R;

public class SegmentedRadioGroup extends RadioGroup {
    public SegmentedRadioGroup(Context context) {
        super(context);
    }

    public SegmentedRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        changeButtonsImages();

    }

    private void changeButtonsImages() {
        int count = super.getChildCount();

        if (count > 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segemnt_radio_button_selector);
            for (int i = 1; i<= count - 1;i++) {
                super.getChildAt(i).setBackgroundResource(R.drawable.segemnt_radio_button_selector);
            }
            super.getChildAt(count - 1).setBackgroundResource(R.drawable.segemnt_radio_button_selector);
        } else if (count == 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segemnt_radio_button_selector);
        }
    }
}
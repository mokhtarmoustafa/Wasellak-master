package com.unicom.wasalakclientproduct.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class SeekBarHint extends SeekBar {
  public SeekBarHint (Context context) {
      super(context);
  }

  public SeekBarHint (Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
  }

  public SeekBarHint (Context context, AttributeSet attrs) {
      super(context, attrs);
  }
    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        int thumb_x = (int) this.getThumb().getBounds().exactCenterX();
        int middle = this.getHeight()/2;
        // your drawing code here, ie Canvas.drawText();
    }
}
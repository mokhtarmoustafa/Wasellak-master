package com.unicom.wasalakclientproduct.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.unicom.wasalakclientproduct.R

class SeekBar  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var progressTextColor: Int? = null
    private var maxValue: Int? = null
    private var thumb: Drawable? = null
    private var progressDrawable: Drawable? = null
    private var startValue: Int? = null


    private var progressTextView: TextView? = null
    private var seekBar: SeekBar? = null

    private var progressValue = MutableLiveData<Int>()
    private val paddingLeftPosition = 30
    private val paddingRightPosition = 25
    private val defLeftPaddingPosition = 120

    init {
        prepareAttributes(attrs)
        prepareLayout()
    }

    private fun prepareAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val attrs = context.obtainStyledAttributes(it, R.styleable.BbynSeekBar, 0, 0)
            try {
                progressTextColor = attrs.getInt(R.styleable.BbynSeekBar_progressTextColor, 0)
                maxValue = attrs.getInt(R.styleable.BbynSeekBar_maxValue, 0)
                thumb = attrs.getDrawable(R.styleable.BbynSeekBar_thumb)
                progressDrawable = attrs.getDrawable(R.styleable.BbynSeekBar_progressDrawable)
                startValue = attrs.getInt(R.styleable.BbynSeekBar_startValue, 0)

            } finally {
                attrs.recycle()
            }
        }
    }


    private fun prepareLayout() {

        val root = LayoutInflater.from(context).inflate(R.layout.seekbar_layout, this, true)

        seekBar = root.findViewById(R.id.bbynSeekBar)
        progressTextView = root.findViewById(R.id.seekbartext) as TextView

        progressTextColor?.let {
            progressTextView?.setTextColor(it)
        }
        maxValue.let { seekBar?.max = it!! }
        thumb.let { seekBar?.thumb = it }
        progressDrawable.let { seekBar?.progressDrawable = progressDrawable }

        startValue?.let {
            seekBar?.progress = it
            setSeeBarText(it)
        }
        seekBarOnChange()

    }

    private fun seekBarOnChange() {

        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                setSeeBarText(progress)

                progressValue.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                setSeekBarDisable(false)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    fun setSeeBarText(text: Int) {
        val thumbRect = seekBar?.thumb?.bounds

        val lp = progressTextView?.layoutParams as ConstraintLayout.LayoutParams

        if (thumbRect!!.left == 0 && text == 1) {
            lp.setMargins(defLeftPaddingPosition, 0, 0, 0)
        } else {
            lp.setMargins(thumbRect.left + paddingLeftPosition, 0, 0, 0)
        }

        progressTextView?.layoutParams = lp
        progressTextView?.text = text.toString()
        progressTextView?.visibility = View.VISIBLE
    }

    fun setSeekBarDisable(value: Boolean) {
        if (value) {
            progressTextView?.setTextColor(ContextCompat.getColor(context, R.color.ashgrey))
            seekBar?.thumb = ContextCompat.getDrawable(context, R.drawable.ic_circle_light_green)
            seekBar?.progressDrawable = ContextCompat.getDrawable(context, R.drawable.ic_circle_shape_grey)
        } else {
            progressTextColor?.let {
                progressTextView?.setTextColor(it)
            }
            progressDrawable.let { seekBar?.progressDrawable = progressDrawable }
            thumb.let { seekBar?.thumb = it }
        }
    }

    fun getProgress(): MutableLiveData<Int> {
        return progressValue
    }


    fun setProgress(value: Int) {
        seekBar?.progress = value
        progressValue.value = value
    }

    fun setMinMax(min: Int, max: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar?.min = min
        }
        startValue = min
        maxValue = max
        setProgress(min)
        prepareLayout()
    }


}
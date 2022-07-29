package com.jayto.jakmall.utils.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.jayto.jakmall.R

class PrimaryButton : FrameLayout {

    var text: AppCompatTextView? = null
    var viewButton: View? = null
    private var textString: String? = ""
    private var backgroundDrawables = ContextCompat.getDrawable(context, R.drawable.bg_rectangle_primary_radius_10)
    private var backgroundSilver = ContextCompat.getDrawable(context, R.drawable.bg_rectangle_silver)
    private var textColor = ContextCompat.getColor(context, R.color.white)
    private var isEnableClick = true

    constructor(context: Context) : super(context) {
        init(null, 0, 0)
    }

    private var textOnClickListener: OnClickListener? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr, 0)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (isInEditMode) return
        val context = context
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.PrimaryButton, defStyleAttr, defStyleRes)
            if (typedArray.hasValue(R.styleable.PrimaryButton_text)) {
                textString = typedArray.getString(R.styleable.PrimaryButton_text)
            }
            if (typedArray.hasValue(R.styleable.PrimaryButton_backgroundDrawables)) {
                backgroundDrawables = typedArray.getDrawable(R.styleable.PrimaryButton_backgroundDrawables)
            }
            if (typedArray.hasValue(R.styleable.PrimaryButton_backgroundDrawablesDisable)) {
                backgroundSilver = typedArray.getDrawable(R.styleable.PrimaryButton_backgroundDrawablesDisable)
            }
            if (typedArray.hasValue(R.styleable.PrimaryButton_enableClick)) {
                isEnableClick = typedArray.getBoolean(R.styleable.PrimaryButton_enableClick, true)
            }
            if (typedArray.hasValue(R.styleable.PrimaryButton_textPrimaryColor)) {
                textColor = typedArray.getColor(R.styleable.PrimaryButton_textPrimaryColor, -1)
            }
            typedArray.recycle()
        }
        viewButton = LayoutInflater.from(context).inflate(R.layout.layout_button_primary, this, false)
        text = viewButton!!.findViewById(R.id.tv_text)
        addView(viewButton)
        text?.text = textString
        text?.setTextColor(textColor)

        var containerBackground = backgroundDrawables

        if (!isEnableClick) {
            this.isEnabled = false
            this.isClickable = false
            containerBackground = backgroundSilver
        }

        viewButton!!.background = containerBackground
    }

    fun getText(): CharSequence {
        return text!!.text
    }

    fun setText(text: CharSequence?) {
        if (text == null) return
        this.text!!.text = text
    }

    fun setTextOnClickListener(onClickListener: OnClickListener?) {
        textOnClickListener = onClickListener
        if (textOnClickListener != null) {
            text!!.setOnClickListener(textOnClickListener)
        }
    }

    fun setEnableClick(enable: Boolean) {
        isEnableClick = enable
        var containerBackground = backgroundDrawables
        if (!isEnableClick) {
            this.isEnabled = false
            this.isClickable = false
            containerBackground = backgroundSilver
        } else {
            this.isEnabled = true
            this.isClickable = true
        }
        viewButton!!.background = containerBackground
    }

    fun isEnableClick(): Boolean {
        return isEnableClick
    }
}
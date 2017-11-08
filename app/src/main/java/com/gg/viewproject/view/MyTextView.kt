package com.gg.viewproject.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import com.gg.viewproject.R

/**
 *  Creator : GG
 *  Time    : 2017/11/3
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain :
 */
class MyTextView : LinearLayout {

    private var mText: String
    private var mTextSize = 15
    private var mTextColor = Color.BLACK
    private var mPaint: Paint

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val array = context?.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        mText = array?.getString(R.styleable.MyTextView_text)!!
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_textSize, sp2Px(mTextSize))
        mTextColor = array.getColor(R.styleable.MyTextView_textColor, mTextColor)
        array.recycle()

        mPaint = Paint()

        //添加抗锯齿
        mPaint.isAntiAlias = true
        //设置画笔绘制文字的size
        mPaint.textSize = mTextSize.toFloat()
        //设置画笔的颜色
        mPaint.color = mTextColor

//        //设置默认背景
//        setBackgroundColor(Color.TRANSPARENT)

        //重新设置Flags
        setWillNotDraw(false)
    }

    private fun sp2Px(sp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), resources.displayMetrics).toInt()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var width = MeasureSpec.getSize(widthMeasureSpec)
        var bound = Rect()
        //通过bound获取绘制的文字的宽高
        mPaint.getTextBounds(mText, 0, mText.length, bound)
        if (widthMode == MeasureSpec.AT_MOST) {

            width = bound.width() + paddingLeft + paddingRight
        }

        var height = MeasureSpec.getSize(heightMeasureSpec)

        if (heightMode == MeasureSpec.AT_MOST) {
            height = bound.height()+paddingTop+paddingBottom
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val fontMetricsInt = mPaint.fontMetricsInt
        Log.d("top", "------   " + fontMetricsInt.top)
        Log.d("bottom", "------   " + fontMetricsInt.bottom)
        Log.d("ascent", "------   " + fontMetricsInt.ascent)
        Log.d("descent", "------   " + fontMetricsInt.descent)
        Log.d("leading", "------   " + fontMetricsInt.leading)

        //计算绘制文字需要的基线
        val baseLine = measuredHeight / 2 + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom

        canvas?.drawText(mText, paddingLeft.toFloat(), baseLine.toFloat(), mPaint)
    }

}


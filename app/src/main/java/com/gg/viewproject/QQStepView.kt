package com.gg.viewproject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

/**
 *  Creator : GG
 *  Time    : 2017/11/7
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain :
 */
class QQStepView : View {

    private var mStepTextSize = 15
    private var mStepTextColor = Color.BLACK
    private var mOuterColor = Color.BLUE
    private var mInnerColor = Color.RED
    private var mBorderWidth = 4

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val array = context?.obtainStyledAttributes(attrs, R.styleable.QQStepView)
        mStepTextColor = array?.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor)!!
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor)
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor)
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, mStepTextSize)
        mBorderWidth = array.getDimensionPixelSize(R.styleable.QQStepView_borderWidth, mBorderWidth)
        array.recycle()


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)


        setMeasuredDimension(if (width >= height) width else height, if (width >= height) width else height)


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //1 画外部的弧
        //2 画内部的弧
        //3 中间的字


    }


}
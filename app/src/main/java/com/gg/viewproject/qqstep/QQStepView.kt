package com.gg.viewproject.qqstep

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.gg.viewproject.R

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

    private var mOuterPaint: Paint
    private var mInnerPaint: Paint
    private var mStepTextPaint: Paint

    private var mDifference: Int = 0
    private var mLeft: Float = 0f
    private var mTop: Float = 0f
    private var mRight: Float = 0f
    private var mBottom: Float = 0f

    private var mMaxStep: Int = 100
    private var mCurrentStep: Int = 50


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val array = context?.obtainStyledAttributes(attrs, R.styleable.QQStepView)
        mStepTextColor = array?.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor)!!
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor)
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor)
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, sp2px(mStepTextSize))
        mBorderWidth = array.getDimensionPixelSize(R.styleable.QQStepView_borderWidth, mBorderWidth)
        array.recycle()

        mOuterPaint = Paint().apply {
            //抗锯齿
            isAntiAlias = true
            //文字的颜色
            color = mOuterColor
            //画笔的宽度
            strokeWidth = mBorderWidth.toFloat()
            //设置边缘弧形
            strokeCap = Paint.Cap.ROUND
            //设置画笔空心
            style = Paint.Style.STROKE
        }
        mInnerPaint = Paint().apply {
            isAntiAlias = true
            color = mInnerColor
            strokeWidth = mBorderWidth.toFloat()
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }
        mStepTextPaint = Paint().apply {
            isAntiAlias = true
            color = mStepTextColor
            textSize = mStepTextSize.toFloat()
        }
    }

    fun sp2px(size: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size.toFloat(), resources.displayMetrics).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val dimension: Int
        if (width <= height) {
            dimension = width
            mDifference = height - width
            mLeft = mBorderWidth / 2f
            mRight = width - mBorderWidth / 2f

            mTop = mBorderWidth / 2f + mDifference / 2
            mBottom = height - mBorderWidth / 2f - mDifference / 2
        } else {
            dimension = height
            mDifference = width - height

            mLeft = mBorderWidth / 2f + mDifference / 2
            mRight = width - mBorderWidth / 2f - mDifference / 2

            mTop = mBorderWidth / 2f
            mBottom = height - mBorderWidth / 2f
        }

        setMeasuredDimension(dimension, dimension)


    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //1 画外部的弧
        val mRectF = RectF(mLeft, mTop, mRight, mBottom)
        //mRectF绘制的区域  startAngle 起始角度  sweepAngle 绘制多少角度  useCenter 是否闭合
        canvas?.drawArc(mRectF, 135f, 270f, false, mOuterPaint)
        //2 画内部的弧

        if (mMaxStep == 0) {
            return
        }
        val sweepAngle = mCurrentStep / mMaxStep.toFloat() * 270
//        Log.d("sweepAngle ------- ", "   ------   " + sweepAngle)
        canvas?.drawArc(mRectF, 135f, sweepAngle, false, mInnerPaint)
        //3 中间的字
        val str = mCurrentStep.toString()

        var bound = Rect()
        mStepTextPaint.getTextBounds(str, 0, str.length, bound)
        val dx = width / 2 - bound.width() / 2

        val mFontMetricsInt = mStepTextPaint.fontMetricsInt
        val baseLine = height / 2 + (mFontMetricsInt.bottom - mFontMetricsInt.top) - mFontMetricsInt.bottom

        canvas?.drawText(str, dx.toFloat(), baseLine.toFloat(), mStepTextPaint)

    }

    fun setMaxStep(maxStep: Int) {
        mMaxStep = maxStep
    }


    fun setCurrentStep(current: Int) {
        mCurrentStep = current
        invalidate()
    }


}
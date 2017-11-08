package com.gg.viewproject.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.DecelerateInterpolator
import com.gg.viewproject.R
import kotlinx.android.synthetic.main.activity_qqstep.*

class QQStepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qqstep)

        qqStepView.setMaxStep(5000)

        //设置属性动画变化的范围  后面就是当前的步数
        val animator = ObjectAnimator.ofFloat(0f, 2000f)
        //设置动画的时间
        animator.duration = 1000
        //设置差值器  DecelerateInterpolator 是先快后慢
        animator.interpolator = DecelerateInterpolator()
        //设置更新的监听 数值变化后把变化的数值 通知控件重新绘制 来获取动画的效果
        animator.addUpdateListener {
            val step = animator.animatedValue as Float
            qqStepView.setCurrentStep(step.toInt())
        }
        animator.start()
    }

}

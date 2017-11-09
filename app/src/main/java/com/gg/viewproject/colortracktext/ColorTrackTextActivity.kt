package com.gg.viewproject.colortracktext

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gg.viewproject.R
import kotlinx.android.synthetic.main.activity_color_track_text.*
import java.lang.Exception

/**
 *  Creator : GG
 *  Time    : 2017/11/9
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain :
 */
class ColorTrackTextActivity : AppCompatActivity() {

    private val tabList = arrayListOf("推荐", "视频", "直播", "图片", "段子")
    private var viewList: ArrayList<ColorTrackTextView> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_track_text)
        initTop()
        initViewPager()
    }

    private fun initViewPager() {
        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ItemFragment.newInstance(tabList[position])
            }

            override fun getCount(): Int {
                return tabList.size
            }
        }

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d("tag", "positionOffset  ---->" + positionOffset
                        + "   position    ---->" + position)
                val left = viewList[position]
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
                left.setCurrentProcess(1 - positionOffset)

                try {
                    val right = viewList[position + 1]
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT)
                    right.setCurrentProcess(positionOffset)

                } catch (e: Exception) {

                }

            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    private fun initTop() {
        tabList.forEach {
            val view = ColorTrackTextView(this)
            view.setChangeColor(Color.RED)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.weight = 1f
            view.layoutParams = params
            view.text = it
            tab.addView(view)
            viewList.add(view)
        }
    }
}
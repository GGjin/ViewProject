package com.gg.viewproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gg.viewproject.colortracktext.ColorTrackTextActivity
import com.gg.viewproject.qqstep.QQStepActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(this, R.layout.item_text, ACTIVITY)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }


    companion object {
        private val ACTIVITY = arrayListOf<Class<*>>(
                QQStepActivity::class.java,
                ColorTrackTextActivity::class.java)
    }


}

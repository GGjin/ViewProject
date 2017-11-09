package com.gg.viewproject.colortracktext

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gg.viewproject.R

/**
 *  Creator : GG
 *  Time    : 2017/11/9
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain :
 */
class ItemFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_item, container, false)
        with(view) {
            val textView: TextView = findViewById(R.id.tv)
            textView.text = arguments.getString(ARGUMENT_TITLE, "hello")
        }
        return view
    }

    companion object {
        const val ARGUMENT_TITLE = "TITLE"
        fun newInstance(str: String?) = ItemFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_TITLE, str)
            }
        }
    }

}
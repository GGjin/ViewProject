package com.gg.viewproject

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *  Creator : GG
 *  Time    : 2017/11/8
 *  Mail    : gg.jin.yu@gmail.com
 *  Explain :
 */
class MainAdapter(context: Context, layoutRes: Int, data: ArrayList<Class<*>>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val mData = data
    private val mLayoutRes = layoutRes
    private val mContext: Context = context

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val mViewHolder = MainViewHolder(LayoutInflater.from(parent?.context).inflate(mLayoutRes, parent, false))
        return mViewHolder
    }

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        holder?.textView?.text = mData[position].name
        holder?.textView?.setOnClickListener {
            val intent = Intent(mContext, mData[position])
            mContext.startActivity(intent)
        }
    }


    class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var textView = itemView?.findViewById<TextView>(R.id.text)
    }


}
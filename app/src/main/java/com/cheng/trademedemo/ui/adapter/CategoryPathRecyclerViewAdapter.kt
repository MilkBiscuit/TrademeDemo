package com.cheng.trademedemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.CategoryPath
import com.cheng.trademedemo.ui.OnListItemClickedListener

class CategoryPathRecyclerViewAdapter(
        private val mValues: List<CategoryPath>,
        private val mListener: OnListItemClickedListener<CategoryPath>)
    : RecyclerView.Adapter<CategoryPathRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_categorypath, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        var categoryName = mValues[position].categoryName
        if (categoryName == "") {
           categoryName = "All Categories"
        }

        holder.mContentView.text = categoryName

        holder.mView.setOnClickListener {
            mListener?.onListItemClicked(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView
        var mItem: CategoryPath? = null

        init {
            mContentView = mView.findViewById(R.id.content)
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}

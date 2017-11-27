package com.cheng.trademedemo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.CategoryPath

import com.cheng.trademedemo.ui.fragment.CategoryPathFragment.OnListFragmentInteractionListener
import android.support.v7.widget.LinearLayoutManager



class CategoryPathRecyclerViewAdapter(private val mValues: List<CategoryPath>,
                                      private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<CategoryPathRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_categorypath, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mContentView.text = mValues[position].categoryName

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
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

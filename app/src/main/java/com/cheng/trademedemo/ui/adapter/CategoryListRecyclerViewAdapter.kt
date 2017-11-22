package com.cheng.trademedemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.SubCategory
import com.cheng.trademedemo.ui.OnListItemClickedListener

class CategoryListRecyclerViewAdapter(
        private val subCategories: List<SubCategory>,
        private val listener: OnListItemClickedListener<SubCategory>?
) : RecyclerView.Adapter<CategoryListRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id)
        val contentView: TextView = view.findViewById(R.id.content)
        var item: SubCategory? = null
    }

    override fun getItemCount(): Int {
        return subCategories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = subCategories[position]
        holder.idView.text = subCategories[position].Number
        holder.contentView.text = subCategories[position].Name

        holder.view.setOnClickListener {
            val item = holder.item!!
            listener?.onListItemClicked(item)
        }
    }
}

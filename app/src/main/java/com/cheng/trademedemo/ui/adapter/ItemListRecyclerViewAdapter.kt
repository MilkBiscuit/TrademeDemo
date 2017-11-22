package com.cheng.trademedemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.BitmapCache
import com.cheng.trademedemo.ui.OnListItemClickedListener

class ItemListRecyclerViewAdapter(
        private val listings: List<Listing>,
        private val listener: OnListItemClickedListener<Listing>?
) : RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: NetworkImageView = view.findViewById(R.id.item_image)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val regionView: TextView = view.findViewById(R.id.item_region)
        var item: Listing? = null
    }

    override fun getItemCount(): Int {
        return listings.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = listings[position]
        holder.titleView.text = listings[position].Title
        holder.regionView.text = listings[position].Region

        val imageUrl = listings[position].PictureHref
        holder.imageView.setErrorImageResId(R.drawable.ic_error)
        holder.imageView.setDefaultImageResId(R.drawable.ic_photo)

        val queue = Volley.newRequestQueue(holder.imageView.context)
        val imageLoader = ImageLoader(queue, BitmapCache())
        holder.imageView.setImageUrl(imageUrl, imageLoader)

        holder.view.setOnClickListener {
            val item = holder.item!!
            listener?.onListItemClicked(item)
        }
    }
}
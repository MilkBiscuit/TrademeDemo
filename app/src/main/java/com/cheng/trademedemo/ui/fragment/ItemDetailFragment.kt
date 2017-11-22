package com.cheng.trademedemo.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
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

class ItemDetailFragment : Fragment() {

    companion object {
        private val KEY_ITEM = "Item"

        fun newInstance(item: Listing): ItemDetailFragment {
            val fragment = ItemDetailFragment()
            fragment.item = item

            return fragment
        }
    }

    private var item: Listing? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_ITEM)) {
                item = savedInstanceState.getParcelable(KEY_ITEM)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelable(KEY_ITEM, item)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_item_detail, container, false)
        initResource(view)

        return view
    }

    private fun initResource(view: View) {
        val imageView = view.findViewById<NetworkImageView>(R.id.item_image)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val listingIdView: TextView = view.findViewById(R.id.item_listing_id)
        val priceView: TextView = view.findViewById(R.id.item_price)

        titleView.text = item!!.Title
        listingIdView.text = item!!.ListingId.toString()
        priceView.text = item!!.PriceDisplay

        val imageUrl = item!!.PictureHref
        imageView.setErrorImageResId(R.drawable.ic_error)
        imageView.setDefaultImageResId(R.drawable.ic_photo)

        val queue = Volley.newRequestQueue(context)
        val imageLoader = ImageLoader(queue, BitmapCache())
        imageView.setImageUrl(imageUrl, imageLoader)
    }

}

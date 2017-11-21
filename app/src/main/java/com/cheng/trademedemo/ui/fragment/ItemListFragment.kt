package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.R
import com.cheng.trademedemo.models.Listing
import com.cheng.trademedemo.ui.adapter.ItemListRecyclerViewAdapter
import com.cheng.trademedemo.ui.OnListItemClickedListener
import java.util.*

class ItemListFragment : Fragment() {

    companion object {
        fun newInstance(listings: List<Listing>): ItemListFragment {
            val fragment = ItemListFragment()
            fragment.listings = listings

            return fragment
        }
    }

    private val KEY_LISTINGS = "Listings";

    private var listings: List<Listing>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_LISTINGS)) {
                listings = savedInstanceState.getParcelableArrayList(KEY_LISTINGS)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_recyclerview, container, false)

        createItemClickListener()

        if (view is RecyclerView) {
            val recyclerView : RecyclerView = view
            val context = view.getContext()
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = (ItemListRecyclerViewAdapter(listings!!, createItemClickListener()))
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelableArrayList(KEY_LISTINGS,
                listings as ArrayList<out Parcelable>)
    }

    private fun createItemClickListener() : OnListItemClickedListener<Listing> {
        return object : OnListItemClickedListener<Listing> {
            override fun onListItemClicked(item: Listing) {

            }
        }
    }
}
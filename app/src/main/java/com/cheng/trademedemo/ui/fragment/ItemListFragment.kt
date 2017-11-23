package com.cheng.trademedemo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.OnListItemClickedListener
import com.cheng.trademedemo.ui.activity.ItemDetailActivity
import com.cheng.trademedemo.ui.activity.MainActivity
import com.cheng.trademedemo.ui.adapter.ItemListRecyclerViewAdapter
import com.cheng.trademedemo.ui.util.FragmentUtil

open class ItemListFragment : Fragment() {

    private val KEY_LISTINGS = "Listings";

    protected var listings: List<Listing>? = null

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

        val recyclerView : RecyclerView = view.findViewById(R.id.list)
        val emptyView : View = view.findViewById(R.id.empty_view)

        recyclerView.adapter = ItemListRecyclerViewAdapter(listings!!, createItemClickListener())
        emptyView.visibility = if (listings!!.isEmpty()) View.VISIBLE else View.GONE

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelableArrayList(KEY_LISTINGS, listings as ArrayList<out Parcelable>)
    }

    private fun createItemClickListener() : OnListItemClickedListener<Listing> {
        return object : OnListItemClickedListener<Listing> {
            override fun onListItemClicked(item: Listing) {
                if (activity is MainActivity) {
                    val mainActivity = activity as MainActivity
                    if (mainActivity.isMultiPaneLayout()) {
                        val itemDetail = ItemDetailFragment.newInstance(item)
                        FragmentUtil.setDetailFragment(fragmentManager, itemDetail,
                                item.ListingId.toString())
                    } else {
                        val intent = Intent(mainActivity, ItemDetailActivity::class.java)
                        intent.putExtra(ItemDetailActivity.EXTRA_KEY_ITEM, item)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
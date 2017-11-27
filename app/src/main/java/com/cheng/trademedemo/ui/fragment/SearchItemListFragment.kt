package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.activity.MainActivity
import com.cheng.trademedemo.ui.util.UIUtil

class SearchItemListFragment : ItemListFragment() {

    companion object {
        private const val KEY_SEARCH_KEYWORD = "Keyword"

        fun newInstance(listings: List<Listing>, searchKeyword: String): SearchItemListFragment {
            val fragment = SearchItemListFragment()
            fragment.listings = listings
            fragment.searchKeyword = searchKeyword

            return fragment
        }
    }

    private var searchKeyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            searchKeyword = savedInstanceState.getString(KEY_SEARCH_KEYWORD)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if (activity is MainActivity) {
            UIUtil.setCategoryPath(activity as MainActivity, "\"" + searchKeyword + "\"",
                    "")
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(KEY_SEARCH_KEYWORD, searchKeyword)
    }

}
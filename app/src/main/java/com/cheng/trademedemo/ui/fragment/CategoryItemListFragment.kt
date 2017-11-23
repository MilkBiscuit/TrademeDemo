package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.activity.MainActivity
import com.cheng.trademedemo.ui.util.UIUtil

class CategoryItemListFragment : ItemListFragment() {

    companion object {
        private const val KEY_CATEGORY_NUM = "CategoryNum"

        fun newInstance(listings: List<Listing>, categoryNum: String): CategoryItemListFragment {
            val fragment = CategoryItemListFragment()
            fragment.listings = listings
            fragment.categoryNum = categoryNum

            return fragment
        }
    }

    private var categoryNum: String = "-1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            categoryNum = savedInstanceState.getString(KEY_CATEGORY_NUM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        UIUtil.setCategoryPath(activity as MainActivity, categoryNum)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(KEY_CATEGORY_NUM, categoryNum)
    }

}
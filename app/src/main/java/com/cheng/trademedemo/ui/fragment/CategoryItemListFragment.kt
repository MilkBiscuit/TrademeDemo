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
        private const val KEY_CATEGORY_PATH = "CategoryPath"

        fun newInstance(listings: List<Listing>, categoryNum: String, categoryPath: String):
                CategoryItemListFragment {
            val fragment = CategoryItemListFragment()
            fragment.listings = listings
            fragment.categoryNum = categoryNum
            fragment.categoryPath = categoryPath

            return fragment
        }
    }

    private var categoryNum: String = "-1"
    private var categoryPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            categoryNum = savedInstanceState.getString(KEY_CATEGORY_NUM)
            categoryPath = savedInstanceState.getString(KEY_CATEGORY_PATH)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if (activity is MainActivity) {
            UIUtil.setCategoryPath(activity as MainActivity, categoryPath, categoryNum)
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(KEY_CATEGORY_NUM, categoryNum)
        outState?.putString(KEY_CATEGORY_PATH, categoryPath)
    }

}
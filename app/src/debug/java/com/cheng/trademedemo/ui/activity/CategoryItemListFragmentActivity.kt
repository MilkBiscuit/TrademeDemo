package com.cheng.trademedemo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.fragment.CategoryItemListFragment
import com.cheng.trademedemo.ui.util.FragmentUtil

class CategoryItemListFragmentActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CATEGORY_NUM = "CategoryNum"
        val EXTRA_CATEGORY_PATH = "CategoryPath"
        val EXTRA_ITEMS = "Items"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        val categoryNum = intent.getStringExtra(EXTRA_CATEGORY_NUM)
        val categoryPath = intent.getStringExtra(EXTRA_CATEGORY_PATH)
        val items = intent.getParcelableArrayListExtra<Listing>(EXTRA_ITEMS)

        if (items.isNotEmpty()) {
            val itemDetailFragment = CategoryItemListFragment.newInstance(items, categoryNum, categoryPath)
            FragmentUtil.setContentForSingleFragmentActivity(supportFragmentManager, itemDetailFragment)
        }
    }
}
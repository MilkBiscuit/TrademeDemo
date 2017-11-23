package com.cheng.trademedemo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.SubCategory
import com.cheng.trademedemo.ui.fragment.CategoryListFragment
import com.cheng.trademedemo.ui.util.FragmentUtil

class CategoryListFragmentActivity : AppCompatActivity() {

    companion object {
        val EXTRA_SUB_CATEGORY = "SubCategory"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        val subCategory = intent.getParcelableExtra<SubCategory>(EXTRA_SUB_CATEGORY)
        if (subCategory != null) {
            val categoryListFragment = CategoryListFragment.newInstance(subCategory)
            FragmentUtil.setContentForSingleFragmentActivity(supportFragmentManager, categoryListFragment)
        }
    }
}
package com.cheng.trademedemo.testrule

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.ui.activity.CategoryItemListFragmentActivity


class CategoryItemListActivityTestRule : ActivityTestRule<CategoryItemListFragmentActivity>(CategoryItemListFragmentActivity::class.java) {

    override fun getActivityIntent(): Intent {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, CategoryItemListFragmentActivity::class.java)
        intent.putExtra(CategoryItemListFragmentActivity.EXTRA_CATEGORY_NUM, MockData.CATEGORY_NUM)
        intent.putExtra(CategoryItemListFragmentActivity.EXTRA_CATEGORY_PATH, MockData.CATEGORY_PATH)
        intent.putParcelableArrayListExtra(CategoryItemListFragmentActivity.EXTRA_ITEMS, MockData.TWO_LISTINGS)

        return intent
    }
}
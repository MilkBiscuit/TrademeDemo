package com.cheng.trademedemo.testrule

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.ui.activity.CategoryListFragmentActivity


class CategoryListActivityTestRule : ActivityTestRule<CategoryListFragmentActivity>(CategoryListFragmentActivity::class.java) {

    override fun getActivityIntent(): Intent {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, CategoryListFragmentActivity::class.java)
        intent.putExtra(CategoryListFragmentActivity.EXTRA_SUB_CATEGORY, MockData.A_SUB_CATEGORY)

        return intent
    }
}
package com.cheng.trademedemo.testrule

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.ui.activity.ItemDetailActivity


class ItemDetailActivityTestRule: ActivityTestRule<ItemDetailActivity>(ItemDetailActivity::class.java) {

    override fun getActivityIntent(): Intent {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, ItemDetailActivity::class.java)
        intent.putExtra(ItemDetailActivity.EXTRA_KEY_ITEM, MockData.A_LISTING)

        return intent
    }
}
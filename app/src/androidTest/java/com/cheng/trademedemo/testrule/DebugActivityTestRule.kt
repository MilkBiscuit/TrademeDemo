package com.cheng.trademedemo.testrule

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.ui.activity.DebugActivity
import com.cheng.trademedemo.ui.activity.ItemDetailActivity


class DebugActivityTestRule : ActivityTestRule<DebugActivity>(DebugActivity::class.java) {

    override fun getActivityIntent(): Intent {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext

        return Intent(targetContext, DebugActivity::class.java)
    }
}
package com.cheng.trademedemo.ui.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.R
import com.cheng.trademedemo.testrule.DebugActivityTestRule
import com.cheng.trademedemo.testrule.ItemDetailActivityTestRule
import org.junit.Rule
import org.junit.Test


class DebugActivityTest {

    @get:Rule
    val activityRule: DebugActivityTestRule = DebugActivityTestRule()

    @Test
    fun checkInstrumentationIsInstalled() {
        checkNotNull(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun searchBoxShown() {
        searchBox().check(matches(isDisplayed()))
    }

    @Test
    fun titleShown() {
        categoryPath().check(matches(isDisplayed()))
        categoryPath().check(matches(withText(R.string.browse_categories)))
    }

    @Test
    fun noDataShown() {
        noData().check(matches(isDisplayed()))
        noData().check(matches(withText("No Data")))
    }

    fun searchBox(): ViewInteraction {
        return onView(withId(R.id.search_trade_me))
    }

    fun categoryPath(): ViewInteraction {
        return onView(withId(R.id.category_path))
    }

    fun noData(): ViewInteraction {
        return onView(withId(R.id.no_data))
    }

}
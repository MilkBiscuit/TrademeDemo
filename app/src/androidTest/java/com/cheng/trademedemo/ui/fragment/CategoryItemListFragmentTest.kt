package com.cheng.trademedemo.ui.fragment

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.R
import com.cheng.trademedemo.testrule.CategoryItemListActivityTestRule
import org.hamcrest.Matchers.hasItem
import org.junit.Rule
import org.junit.Test

class CategoryItemListFragmentTest {
    @get:Rule
    val activityRule: CategoryItemListActivityTestRule = CategoryItemListActivityTestRule()

    @Test
    fun checkInstrumentationIsInstalled() {
        checkNotNull(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun listShown() {
        list().check(matches(ViewMatchers.isDisplayed()))
        onData(hasItem(MockData.A_LISTING)).inAdapterView(ViewMatchers.withId(R.id.list))
    }

    fun list(): ViewInteraction {
        return Espresso.onView(ViewMatchers.withId(R.id.list))
    }

}
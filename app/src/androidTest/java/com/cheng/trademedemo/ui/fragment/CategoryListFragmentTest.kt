package com.cheng.trademedemo.ui.fragment

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.R
import com.cheng.trademedemo.testrule.CategoryListActivityTestRule
import org.hamcrest.Matchers.hasItem
import org.junit.Rule
import org.junit.Test

class CategoryListFragmentTest {
    @get:Rule
    val activityRule: CategoryListActivityTestRule = CategoryListActivityTestRule()

    @Test
    fun checkInstrumentationIsInstalled() {
        checkNotNull(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun listShown() {
        list().check(ViewAssertions.matches(isDisplayed()))
        // RecyclerView is not a sub-class of AdapterView, won't do the click and check the result
        onData(hasItem(MockData.A_SUB_CATEGORY)).inAdapterView(withId(R.id.list))
    }

    fun list(): ViewInteraction {
        return Espresso.onView(withId(R.id.list))
    }

}
package com.cheng.trademedemo.ui.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.cheng.trademedemo.MockData
import com.cheng.trademedemo.R
import com.cheng.trademedemo.testrule.ItemDetailActivityTestRule
import org.junit.Rule
import org.junit.Test


class ItemDetailActivityTest {

    @get:Rule
    val activityRule: ItemDetailActivityTestRule = ItemDetailActivityTestRule()

    @Test
    fun checkInstrumentationIsInstalled() {
        checkNotNull(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun imageShown() {
        itemImage().check(matches(isDisplayed()))
    }

    @Test
    fun titleShown() {
        itemTitle().check(matches(isDisplayed()))
        itemTitle().check(matches(withText(MockData.ITEM_TITLE)))
    }

    @Test
    fun listingIdShown() {
        itemListingId().check(matches(isDisplayed()))
        itemListingId().check(matches(withText(MockData.LISTING_ID.toString())))
    }

    @Test
    fun priceShown() {
        itemPrice().check(matches(isDisplayed()))
        itemPrice().check(matches(withText(MockData.PRICE)))
    }

    fun itemImage(): ViewInteraction {
        return onView(withId(R.id.item_image))
    }

    fun itemTitle(): ViewInteraction {
        return onView(withId(R.id.item_title))
    }

    fun itemListingId(): ViewInteraction {
        return onView(withId(R.id.item_listing_id))
    }

    fun itemPrice(): ViewInteraction {
        return onView(withId(R.id.item_price))
    }
}
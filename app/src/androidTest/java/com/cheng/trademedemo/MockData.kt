package com.cheng.trademedemo

import android.os.Parcelable
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.model.SubCategory

object MockData {
    const val ITEM_TITLE = "Mens Fashionista Overcoat"
    const val LISTING_ID: Long = 6194276
    const val CATEGORY_NUM = "0153-0438-3720-"
    const val CATEGORY_PATH = "/Clothing-Fashion/Men/Jackets"
    const val PICTURE_HREF = "https://images.tmsandbox.co.nz/photoserver/thumb/893921.jpg"
    const val PRICE = "$117.00"

    val A_LISTING = Listing(
            LISTING_ID,
            ITEM_TITLE,
            CATEGORY_NUM,
            PICTURE_HREF,
            true,
            "Auckland",
            "Auckland City",
            PRICE
    )

    val TWO_LISTINGS = arrayListOf<Parcelable>(A_LISTING, A_LISTING)

    val CAR_LEAF_SUB_CATEGORY = SubCategory(
            "Cars",
            "0001-0268-",
            "/Trade-Me-Motors/Cars",
            null,
            true
    )
    val MOTORBIKE_LEAF_SUB_CATEGORY = SubCategory(
            "Motorbikes",
            "0001-0026-",
            "/Trade-Me-Motors/Motorbikes",
            null,
            true
    )
    val A_SUB_CATEGORY = SubCategory(
            "Trade Me Motors",
            "0001-",
            "/Trade-Me-Motors",
            listOf(CAR_LEAF_SUB_CATEGORY, MOTORBIKE_LEAF_SUB_CATEGORY),
            false
    )

}
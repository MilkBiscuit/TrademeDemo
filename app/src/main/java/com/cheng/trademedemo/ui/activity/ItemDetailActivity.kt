package com.cheng.trademedemo.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.Listing
import com.cheng.trademedemo.ui.fragment.ItemDetailFragment
import com.cheng.trademedemo.ui.util.FragmentUtil

class ItemDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY_ITEM = "Item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

        val item: Listing = intent.extras.getParcelable(EXTRA_KEY_ITEM)
        val itemDetailFragment = ItemDetailFragment.newInstance(item)
        FragmentUtil.setContentForSingleFragmentActivity(supportFragmentManager, itemDetailFragment)
    }
}

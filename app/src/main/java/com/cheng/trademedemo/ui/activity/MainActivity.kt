package com.cheng.trademedemo.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.TextView
import com.cheng.trademedemo.R
import com.cheng.trademedemo.service.TradeMeApiInterface
import com.cheng.trademedemo.service.TradeMeApiService
import com.cheng.trademedemo.ui.fragment.*
import com.cheng.trademedemo.ui.util.FragmentUtil
import com.cheng.trademedemo.ui.util.UIUtil

open class MainActivity : AppCompatActivity() {

    private val KEY_HAS_DATA = "hasData"

    private var hasData: Boolean = false
    private var tvNoData: TextView? = null
    private var progressLayout: View? = null
    private var tradeMeApiInterface: TradeMeApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            hasData = savedInstanceState.getBoolean(KEY_HAS_DATA)
        }

        tradeMeApiInterface = TradeMeApiService.getApiInterface()

        setContentView(R.layout.activity_main)
        initResources()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putBoolean(KEY_HAS_DATA, hasData)
    }

    fun isMultiPaneLayout() : Boolean {
        val detailFragment = findViewById<View>(R.id.detail_fragment)

        return detailFragment != null;
    }

    open fun initResources() {
        progressLayout = findViewById(R.id.progress)
        tvNoData = findViewById(R.id.no_data)

        if (hasData) {
            tvNoData!!.visibility = View.GONE
        } else {
            tvNoData!!.visibility = View.VISIBLE

            refreshProgressBar(true);
            TradeMeApiService.requestCategories(
                    this,
                    { response ->
                        refreshProgressBar(false)
                        hideNoDataHint()

                        val categoryList = CategoryListFragment.newInstance(response)
                        FragmentUtil.setCategoryListFragment(supportFragmentManager, categoryList)
                    },
                    {
                        refreshProgressBar(false)
                    }
            )
        }

        val searchView : SearchView = findViewById(R.id.search_trade_me)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String): Boolean {
                searchView.clearFocus()
                refreshProgressBar(true)

                val activity = this@MainActivity
                TradeMeApiService.searchKeyword(
                        activity,
                        keyword,
                        { response ->
                            refreshProgressBar(false)

                            if (response.List != null) {
                                val listings = response.List
                                val searchItemFragment = SearchItemListFragment.newInstance(
                                        listings, keyword)
                                FragmentUtil.setCategoryListFragment(
                                        supportFragmentManager,
                                        searchItemFragment,
                                        keyword)
                            }
                        },
                        {
                            refreshProgressBar(false)
                        }
                )

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        addEmptyDetailForMultiPane()
    }

    private fun hideNoDataHint() {
        hasData = true
        tvNoData!!.visibility = View.GONE
    }

    private fun refreshProgressBar(show: Boolean) {
        progressLayout!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun getDetailFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.detail_fragment)
    }

    private fun addEmptyDetailForMultiPane() {
        if (isMultiPaneLayout()) {
            val detailFragment = getDetailFragment()
            if (detailFragment == null) {
                val logoFragment = LogoFragment()
                FragmentUtil.setDetailFragment(supportFragmentManager, logoFragment)
            }
        }
    }

}

package com.cheng.trademedemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.TextView
import com.cheng.trademedemo.R
import com.cheng.trademedemo.models.CategoryResponse
import com.cheng.trademedemo.models.SearchResponse
import com.cheng.trademedemo.service.TradeMeApiInterface
import com.cheng.trademedemo.service.TradeMeApiService
import com.cheng.trademedemo.ui.fragment.CategoryListFragment
import com.cheng.trademedemo.ui.util.FragmentUtil
import com.cheng.trademedemo.ui.util.UIUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
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

    private fun initResources() {
        progressLayout = findViewById(R.id.progress)
        tvNoData = findViewById(R.id.no_data)

        if (hasData) {
            tvNoData!!.visibility = View.GONE
        } else {
            tvNoData!!.visibility = View.VISIBLE

            refreshProgressBar(true);
            requestCategories()
        }

        val searchView : SearchView = findViewById(R.id.search_trade_me)
        searchView.setQuery("iPhone", false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String): Boolean {
                searchView.clearFocus()
                refreshProgressBar(true)

                searchKeyword(keyword)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun requestCategories() {
        val call = tradeMeApiInterface!!.fetchCategories()
        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                val statusCode = response.code()
                if (statusCode == TradeMeApiService.SUCCESS_CODE) {
                    val categoryResponse = response.body()
                    if (categoryResponse != null) {
                        hideNoDataHint()

                        val categoryListFragment = CategoryListFragment.newInstance(categoryResponse)
                        FragmentUtil.setContentFragment(supportFragmentManager, categoryListFragment, false)
                    }
                } else {
                    UIUtil.showToast(this@MainActivity, response.message())
                }

                refreshProgressBar(false)
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                val message = t.localizedMessage
                UIUtil.showToast(this@MainActivity, message)
            }
        })
    }

    private fun searchKeyword(keyword: String) {
        val call = tradeMeApiInterface!!.searchKeyword(keyword)
        call.enqueue(object: Callback<SearchResponse> {

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
                val statusCode = response.code()
                if (statusCode == TradeMeApiService.SUCCESS_CODE) {
                    val searchResponse = response.body()
                    if (searchResponse != null) {

                    }
                } else {
                    UIUtil.showToast(this@MainActivity, response.message())
                }

                refreshProgressBar(false)
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                val message = t?.localizedMessage
                if (message != null) {
                    UIUtil.showToast(this@MainActivity, message)
                }

                refreshProgressBar(false)
            }

        })
    }

    private fun hideNoDataHint() {
        hasData = true
        tvNoData!!.visibility = View.GONE
    }

    private fun refreshProgressBar(show: Boolean) {
        progressLayout!!.visibility = if (show) View.VISIBLE else View.GONE
    }

}

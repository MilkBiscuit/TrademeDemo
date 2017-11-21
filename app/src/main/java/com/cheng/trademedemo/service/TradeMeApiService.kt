package com.cheng.trademedemo.service

import android.content.Context
import com.cheng.trademedemo.models.SearchResponse
import com.cheng.trademedemo.ui.util.UIUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object TradeMeApiService {

    const val SUCCESS_CODE = 200

    private val WEB_SERVICE_BASE_URL = "https://api.tmsandbox.co.nz/v1/"
    private var apiInterface: TradeMeApiInterface? = null

    fun getApiInterface(): TradeMeApiInterface {
        if (apiInterface == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(WEB_SERVICE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            apiInterface = retrofit.create(TradeMeApiInterface::class.java)
        }

        return apiInterface!!
    }

    fun searchCategory(
            context: Context,
            categoryNumber: String,
            successCallback: (searchResponse: SearchResponse) -> Unit,
            failCallback: () -> Unit ) {

        val tradeMeApiInterface = getApiInterface()
        val call = tradeMeApiInterface.searchCategory(categoryNumber)
        call.enqueue(object: Callback<SearchResponse> {

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
                val searchResponse = response.body()
                if (searchResponse != null) {
                    successCallback(searchResponse)
                }

                if (response.code() != SUCCESS_CODE) {
                    UIUtil.showToast(context, response.message())
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                failCallback()

                val message = t?.localizedMessage
                if (message != null) {
                    UIUtil.showToast(context, message)
                }
            }

        })
    }

}
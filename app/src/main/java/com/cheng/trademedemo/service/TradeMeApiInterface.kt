package com.cheng.trademedemo.service

import com.cheng.trademedemo.model.CategoryResponse
import com.cheng.trademedemo.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TradeMeApiInterface {

    @GET("Categories/0.json")
    fun fetchCategories(): Call<CategoryResponse>

    @Headers("Authorization: OAuth oauth_consumer_key=\"A1AC63F0332A131A78FAC304D007E7D1\"," +
            "oauth_signature_method=\"PLAINTEXT\",oauth_timestamp=\"1511177219\"," +
            "oauth_nonce=\"Wa7hrTHqltg\",oauth_version=\"1.0\"," +
            "oauth_signature=\"EC7F18B17A062962C6930A8AE88B16C7%26\"")
    @GET("Search/General.json")
    fun searchKeyword(@Query("search_string") keyword: String): Call<SearchResponse>

    @Headers("Authorization: OAuth oauth_consumer_key=\"A1AC63F0332A131A78FAC304D007E7D1\"," +
            "oauth_signature_method=\"PLAINTEXT\",oauth_timestamp=\"1511177219\"," +
            "oauth_nonce=\"Wa7hrTHqltg\",oauth_version=\"1.0\"," +
            "oauth_signature=\"EC7F18B17A062962C6930A8AE88B16C7%26\"")
    @GET("Search/General.json")
    fun searchCategory(@Query("category") categoryNumber: String): Call<SearchResponse>

}
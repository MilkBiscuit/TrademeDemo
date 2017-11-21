package com.cheng.trademedemo.models

data class SearchResponse (
    val TotalCount: Int = 0,
    val Page: Int = 0,
    val PageSize: Int = 0,
    val DidYouMean: String? = null,
    val List: List<Listing>? = null,
    val FoundCategories: List<FoundCategory>? = null
)
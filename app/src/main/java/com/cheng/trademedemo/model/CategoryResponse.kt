package com.cheng.trademedemo.model

class CategoryResponse (
        Name: String? = null,
        Number: String? = null,
        Path: String? = null,
        IsLeaf: Boolean = false,
        Subcategories: List<SubCategory>? = null
) : SubCategory (Name, Number, Path, Subcategories, IsLeaf)
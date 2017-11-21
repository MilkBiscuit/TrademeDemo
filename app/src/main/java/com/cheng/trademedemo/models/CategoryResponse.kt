package com.cheng.trademedemo.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
        @SerializedName("Name")
        val name: String? = null,

        @SerializedName("Number")
        val number: String? = null,

        @SerializedName("Path")
        val path: String? = null,

        @SerializedName("IsLeaf")
        val isLeaf: Boolean = false,

        @SerializedName("Subcategories")
        val subcategories: List<SubCategory>? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            ArrayList<SubCategory>().apply { source.readList(this, SubCategory::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(number)
        writeString(path)
        writeInt((if (isLeaf) 1 else 0))
        writeList(subcategories)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CategoryResponse> = object : Parcelable.Creator<CategoryResponse> {
            override fun createFromParcel(source: Parcel): CategoryResponse = CategoryResponse(source)
            override fun newArray(size: Int): Array<CategoryResponse?> = arrayOfNulls(size)
        }
    }
}
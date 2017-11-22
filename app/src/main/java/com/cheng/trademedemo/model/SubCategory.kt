package com.cheng.trademedemo.model

import android.os.Parcel
import android.os.Parcelable

open class SubCategory(
        val Name: String? = null,
        val Number: String? = null,
        val Path: String? = null,
        val Subcategories: List<SubCategory>? = null,
        val IsLeaf: Boolean = false
) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            ArrayList<SubCategory>().apply { source.readList(this, SubCategory::class.java.classLoader) },
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(Name)
        writeString(Number)
        writeString(Path)
        writeList(Subcategories)
        writeInt((if (IsLeaf) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SubCategory> = object : Parcelable.Creator<SubCategory> {
            override fun createFromParcel(source: Parcel): SubCategory = SubCategory(source)
            override fun newArray(size: Int): Array<SubCategory?> = arrayOfNulls(size)
        }
    }
}
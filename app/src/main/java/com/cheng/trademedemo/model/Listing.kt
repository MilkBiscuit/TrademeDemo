package com.cheng.trademedemo.model

import android.os.Parcel
import android.os.Parcelable

data class Listing(
        val ListingId: Long = 0,
        val Title: String? = null,
        val Category: String? = null,
        val PictureHref: String? = null,
        val IsNew: Boolean = true,
        val Region: String? = null,
        val Suburb: String? = null,
        val PriceDisplay: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(ListingId)
        writeString(Title)
        writeString(Category)
        writeString(PictureHref)
        writeInt((if (IsNew) 1 else 0))
        writeString(Region)
        writeString(Suburb)
        writeString(PriceDisplay)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Listing> = object : Parcelable.Creator<Listing> {
            override fun createFromParcel(source: Parcel): Listing = Listing(source)
            override fun newArray(size: Int): Array<Listing?> = arrayOfNulls(size)
        }
    }
}
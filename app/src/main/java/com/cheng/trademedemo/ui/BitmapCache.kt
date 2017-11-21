package com.cheng.trademedemo.ui

import android.graphics.Bitmap
import android.support.v4.util.LruCache
import com.android.volley.toolbox.ImageLoader

class BitmapCache : ImageLoader.ImageCache {

    val cache: LruCache<String, Bitmap> = LruCache<String, Bitmap>(20)

    override fun getBitmap(url: String?): Bitmap? {
        val bitmap = cache.get(url)

        return bitmap
    }

    override fun putBitmap(url: String?, bitmap: Bitmap?) {
        cache.put(url, bitmap)
    }

}
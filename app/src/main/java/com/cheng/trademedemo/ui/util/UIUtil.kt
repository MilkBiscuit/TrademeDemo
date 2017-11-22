package com.cheng.trademedemo.ui.util

import android.content.Context
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.cheng.trademedemo.R
import com.cheng.trademedemo.ui.activity.MainActivity

object UIUtil {

    private var toast: Toast? = null

    fun setCategoryPath(activity: MainActivity, path: String) {
        val categoryPathView = activity.findViewById<TextView>(R.id.category_path)
        if (TextUtils.isEmpty(path)) {
            categoryPathView.setText(R.string.browse_categories)
        } else {
            categoryPathView.text = path
        }
    }

    fun showToast(context: Context, text: String) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(text)
        }

        toast!!.show()
    }

}
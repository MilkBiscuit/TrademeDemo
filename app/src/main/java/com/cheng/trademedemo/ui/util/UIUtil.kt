package com.cheng.trademedemo.ui.util

import android.content.Context
import android.widget.Toast

object UIUtil {

    private var toast: Toast? = null

    fun showToast(context: Context, text: String) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(text)
        }

        toast!!.show()
    }

}
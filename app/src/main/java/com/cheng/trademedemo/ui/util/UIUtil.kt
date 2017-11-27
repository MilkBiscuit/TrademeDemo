package com.cheng.trademedemo.ui.util

import android.content.Context
import android.widget.Toast
import com.cheng.trademedemo.R
import com.cheng.trademedemo.ui.activity.MainActivity
import com.cheng.trademedemo.ui.fragment.CategoryPathFragment

object UIUtil {

    private var toast: Toast? = null

    fun setCategoryPath(activity: MainActivity, pathName: String, pathNumber: String) {
        val header = CategoryPathFragment.newInstance(pathName, pathNumber)
        FragmentUtil.setContentFragment(activity.supportFragmentManager, header,
                R.id.category_path, null)
    }

    fun showToast(context: Context, text: String) {
        if (toast == null) {
            toast = Toast.makeText(context.applicationContext, text, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(text)
        }

        toast!!.show()
    }

}
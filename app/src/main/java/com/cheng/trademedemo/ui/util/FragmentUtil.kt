package com.cheng.trademedemo.ui.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import com.cheng.trademedemo.R

object FragmentUtil {

    fun setCategoryListFragment(fragmentManager: FragmentManager, fragment: Fragment,
                                backStackName: String? = null) {
        setContentFragment(fragmentManager, fragment, R.id.category_list, backStackName)
    }

    fun setContentForSingleFragmentActivity(fragmentManager: FragmentManager, fragment: Fragment) {
        setContentFragment(fragmentManager, fragment, R.id.content_fragment, null)
    }

    fun setDetailFragment(fragmentManager: FragmentManager, fragment: Fragment, backStackName: String? = null) {
        setContentFragment(fragmentManager, fragment, R.id.detail_fragment, backStackName)
    }

    fun setContentFragment(fragmentManager: FragmentManager, fragment: Fragment,
                           @IdRes id: Int, backStackName: String?) {
        val fragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(id, fragment)

        if (!TextUtils.isEmpty(backStackName)) {
            fragmentTransaction.addToBackStack(backStackName!!)
        }

        fragmentTransaction.commit()
    }

}
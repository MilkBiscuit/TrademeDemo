package com.cheng.trademedemo.ui.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.cheng.trademedemo.R

object FragmentUtil {

    fun setContentFragment(fragmentManager: FragmentManager, fragment: Fragment,
                           addToBackStack: Boolean, backStackName: String? = null) {
        val fragmentTransaction = fragmentManager
                .beginTransaction()
                .replace(R.id.category_list, fragment)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(backStackName!!)
        }

        fragmentTransaction.commit()
    }

}
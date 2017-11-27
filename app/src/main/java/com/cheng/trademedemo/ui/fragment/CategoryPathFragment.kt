package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.CategoryPath
import com.cheng.trademedemo.ui.adapter.CategoryPathRecyclerViewAdapter
import com.cheng.trademedemo.ui.OnListItemClickedListener

class CategoryPathFragment : Fragment() {

    companion object {

        private val ARG_PATH_NAME = "PathName"
        private val ARG_PATH_NUMBER = "PathNumber"

        fun newInstance(pathName: String, pathNum: String): CategoryPathFragment {
            val fragment = CategoryPathFragment()
            val args = Bundle()
            args.putString(ARG_PATH_NAME, pathName)
            args.putString(ARG_PATH_NUMBER, pathNum)
            fragment.arguments = args
            return fragment
        }
    }

    private var pathNumber = ""
    private var pathName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            pathNumber = arguments.getString(ARG_PATH_NUMBER)
            pathName = arguments.getString(ARG_PATH_NAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_categorypath_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()

            val pathNums = pathNumber.split("-".toRegex())
            val pathNames = pathName.split("/".toRegex())
            val categoryPaths: MutableList<CategoryPath> = ArrayList<CategoryPath>()
            var i = 0
            while (i < pathNums.size) {
                categoryPaths.add(CategoryPath(pathNums[i], pathNames[i]))
                i++
            }
            view.adapter = CategoryPathRecyclerViewAdapter(categoryPaths, createItemClickListener())

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                    false)
            view.layoutManager = layoutManager
        }
        return view
    }

    private fun createItemClickListener() : OnListItemClickedListener<CategoryPath> {
        return object : OnListItemClickedListener<CategoryPath> {
            override fun onListItemClicked(item: CategoryPath) {
                fragmentManager.popBackStack(item.categoryNum, POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

}

package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.R
import com.cheng.trademedemo.model.SubCategory
import com.cheng.trademedemo.service.TradeMeApiService
import com.cheng.trademedemo.ui.activity.MainActivity
import com.cheng.trademedemo.ui.OnListItemClickedListener
import com.cheng.trademedemo.ui.adapter.CategoryListRecyclerViewAdapter
import com.cheng.trademedemo.ui.util.FragmentUtil
import com.cheng.trademedemo.ui.util.UIUtil

class CategoryListFragment : Fragment() {

    companion object {
        fun newInstance(subCategory: SubCategory): CategoryListFragment {
            val fragment = CategoryListFragment()
            fragment.subCategory = subCategory

            return fragment
        }
    }

    private val KEY_SUB_CATEGORY = "SubCategory";

    private var subCategory: SubCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_SUB_CATEGORY)) {
                subCategory = savedInstanceState.getParcelable(KEY_SUB_CATEGORY)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_recyclerview, container, false)

        UIUtil.setCategoryPath(activity as MainActivity, subCategory!!.Path!!)

        if (view is RecyclerView) {
            val recyclerView : RecyclerView = view
            val context = view.getContext()
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = CategoryListRecyclerViewAdapter(subCategory!!.Subcategories!!,
                    createItemClickListener())
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelable(KEY_SUB_CATEGORY, subCategory)
    }

    private fun createItemClickListener() : OnListItemClickedListener<SubCategory> {
        return object : OnListItemClickedListener<SubCategory> {
            override fun onListItemClicked(item: SubCategory) {
                if (item.IsLeaf) {
                    UIUtil.setCategoryPath(activity as MainActivity, item.Path!!)

                    TradeMeApiService.searchCategory(
                            context,
                            item.Number!!,
                            {searchResponse ->
                                if (searchResponse.List != null) {
                                    val listings = searchResponse.List
                                    val fragment = ItemListFragment.newInstance(listings)
                                    FragmentUtil.setCategoryListFragment(fragmentManager, fragment,
                                            item.Path)
                                }
                            },
                            {})
                } else {
                    val fragment = newInstance(item)
                    FragmentUtil.setCategoryListFragment(fragmentManager, fragment, item.Name)
                }
            }
        }
    }

}

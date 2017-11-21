package com.cheng.trademedemo.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.trademedemo.R
import com.cheng.trademedemo.models.SubCategory
import com.cheng.trademedemo.service.TradeMeApiService
import com.cheng.trademedemo.ui.adapter.CategoryListRecyclerViewAdapter
import com.cheng.trademedemo.ui.util.FragmentUtil
import com.cheng.trademedemo.ui.OnListItemClickedListener
import java.util.ArrayList

class CategoryListFragment : Fragment() {

    companion object {
        fun newInstance(subCategories: List<SubCategory>): CategoryListFragment {
            val fragment = CategoryListFragment()
            fragment.subCategories = subCategories

            return fragment
        }
    }

    private val KEY_SUB_CATEGORIES = "SubCategories";
    private val TRANSACTION_NAME_SEARCH_RESULTS = "SearchResults";

    private var subCategories: List<SubCategory>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_SUB_CATEGORIES)) {
                subCategories = savedInstanceState.getParcelableArrayList(KEY_SUB_CATEGORIES)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_recyclerview, container, false)

        createItemClickListener()

        if (view is RecyclerView) {
            val recyclerView : RecyclerView = view
            val context = view.getContext()
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = CategoryListRecyclerViewAdapter(subCategories!!, createItemClickListener())
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putParcelableArrayList(KEY_SUB_CATEGORIES,
                subCategories as ArrayList<out Parcelable>)
    }

    private fun createItemClickListener() : OnListItemClickedListener<SubCategory> {
        return object : OnListItemClickedListener<SubCategory> {
            override fun onListItemClicked(item: SubCategory) {
                if (item.IsLeaf) {
                    TradeMeApiService.searchCategory(
                            context,
                            item.Number!!,
                            {searchResponse ->
                                if (searchResponse.List != null) {
                                    val listings = searchResponse.List
                                    val fragment = ItemListFragment.newInstance(listings)
                                    FragmentUtil.setContentFragment(fragmentManager, fragment,
                                            true, TRANSACTION_NAME_SEARCH_RESULTS)
                                }
                            },
                            {})
                } else {
                    val fragment = newInstance(item.Subcategories!!)
                    FragmentUtil.setContentFragment(fragmentManager, fragment,
                            true, item.Name)
                }
            }
        }
    }
}

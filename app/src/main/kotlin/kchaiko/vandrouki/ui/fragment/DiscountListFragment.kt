package kchaiko.vandrouki.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.extensions.WRAP_CONTENT
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.extensions.getThemeAttrColor
import kchaiko.vandrouki.navigation.Screens
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.ui.recycler.RecyclerAdapter
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.DiscountItem
import kchaiko.vandrouki.ui.recycler.item.NavigationItem
import kchaiko.vandrouki.viewmodel.provide.DiscountListViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel


class DiscountListFragment : BaseFragment() {

    companion object {
        fun newInstance() = DiscountListFragment()
    }

    private val viewModel: DiscountListViewModel by viewModel()
    private var page = DEFAULT_PAGE
    private lateinit var adapter: RecyclerAdapter

    private lateinit var rvDiscountList: RecyclerView
    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingDelegate { showLoadingIndicator(it) }
                .dataDelegate {
                    rvDiscountList.scrollToPosition(0)
                    adapter.addItems(getShowingItems(it))
                }
                .errorDelegate { proceedError(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            DiscountListUI().createView(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::adapter.isInitialized) {
            adapter = RecyclerAdapter {
                router.navigateTo(Screens.Discount((this as DiscountItem).discountBean))
            }
            viewModel.provideData()
        }
        rvDiscountList.adapter = adapter
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        rvDiscountList.visibility = if (showLoading) View.GONE else View.VISIBLE
        pbLoading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun getShowingItems(discountList: List<Discount>): MutableList<BaseItem> = discountList.map { discount ->
        DiscountItem(discount)
    }.plus(NavigationItem(page) {
        page = it
        viewModel.loadDataByPage(page)
    }).toMutableList()

    private class DiscountListUI : AnkoComponent<DiscountListFragment> {
        override fun createView(ui: AnkoContext<DiscountListFragment>): View = with(ui) {
            frameLayout {
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorDefault)
                fitsSystemWindows = true
                recyclerView {
                    ui.owner.rvDiscountList = this
                    layoutManager = LinearLayoutManager(ui.ctx)
                    clipToPadding = true
                }.lparams(width = MATCH_PARENT, height = MATCH_PARENT)
                progressBar {
                    ui.owner.pbLoading = this
                    visibility = View.GONE
                }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT, gravity = Gravity.CENTER)
            }
        }
    }

}
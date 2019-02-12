package kchaiko.vandrouki.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.extensions.observe
import kchaiko.vandrouki.ui.component.fragment.DiscountListUI
import kchaiko.vandrouki.ui.recycler.adapter.DiscountRecyclerAdapter
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.DiscountItem
import kchaiko.vandrouki.ui.recycler.item.NavigationItem
import kchaiko.vandrouki.viewmodel.DiscountListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DiscountListFragment : BaseFragment() {

    companion object {
        private const val PAGE_ARG = "page"
    }

    override val viewModel: DiscountListViewModel by viewModel {
        parametersOf(arguments?.getInt(PAGE_ARG))
    }
    private lateinit var adapter: DiscountRecyclerAdapter

    lateinit var rvDiscountList: RecyclerView
    lateinit var pbLoading: ProgressBar

    override fun bindLiveData() {
        super.bindLiveData()
        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            adapter.addItems(getShowingItems(this))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            DiscountListUI().createView(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!::adapter.isInitialized) {
            adapter = DiscountRecyclerAdapter {
                navigateToDiscountDetailed((this as DiscountItem).discount)
            }
        }
        rvDiscountList.layoutManager = LinearLayoutManager(context)
        rvDiscountList.adapter = adapter
    }

    override fun showLoadingIndicator(showLoading: Boolean) {
        rvDiscountList.visibility = if (showLoading) View.GONE else View.VISIBLE
        pbLoading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun getShowingItems(discountList: List<Discount>): MutableList<BaseItem> = discountList.map { discount ->
        DiscountItem(discount)
    }.plus(NavigationItem(viewModel.page) {
        viewModel.loadDataByPage(it)
        rvDiscountList.scrollToPosition(0)
    }).toMutableList()

    private fun navigateToDiscountDetailed(discount: Discount) {
        findNavController().navigate(
                R.id.toDiscountFragment,
                bundleOf(DiscountFragment.DISCOUNT_ARG to discount)
        )
    }

}
package kchaiko.vandrouki.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.navigation.Screens
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.ui.component.fragment.DiscountListUI
import kchaiko.vandrouki.ui.recycler.adapter.DiscountRecyclerAdapter
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.DiscountItem
import kchaiko.vandrouki.ui.recycler.item.NavigationItem
import kchaiko.vandrouki.viewmodel.DiscountListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountListFragment : BaseFragment() {

    companion object {
        fun newInstance() = DiscountListFragment()
    }

    private val viewModel: DiscountListViewModel by viewModel()
    private var page = DEFAULT_PAGE
    private lateinit var adapter: DiscountRecyclerAdapter

    lateinit var rvDiscountList: RecyclerView
    lateinit var pbLoading: ProgressBar

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
            adapter = DiscountRecyclerAdapter {
                router.navigateTo(Screens.Discount((this as DiscountItem).discount))
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

}
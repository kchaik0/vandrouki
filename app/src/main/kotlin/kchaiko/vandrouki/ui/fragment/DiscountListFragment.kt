package kchaiko.vandrouki.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.navigation.Screens
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.ui.recycler.RecyclerAdapter
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.DiscountItem
import kchaiko.vandrouki.ui.recycler.item.NavigationItem
import kchaiko.vandrouki.viewmodel.provide.DiscountListViewModel
import kotlinx.android.synthetic.main.fragment_discount_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class DiscountListFragment : BaseFragment() {

    companion object {
        fun newInstance() = DiscountListFragment()
    }

    private val viewModel: DiscountListViewModel by viewModel()
    private var page = DEFAULT_PAGE
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingDelegate { showLoadingIndicator(it) }
                .dataDelegate {
                    fdl_discount_list.scrollToPosition(0)
                    adapter.addItems(getShowingItems(it))
                }
                .errorDelegate { proceedError(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_discount_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::adapter.isInitialized) {
            adapter = RecyclerAdapter {
                router.navigateTo(Screens.Discount((this as DiscountItem).discountBean))
            }
            viewModel.provideData()
        }
        fdl_discount_list.adapter = adapter
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        fdl_discount_list.visibility = if (showLoading) View.GONE else View.VISIBLE
        fdl_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun getShowingItems(discountList: List<Discount>): MutableList<BaseItem> = discountList.map { discount ->
        DiscountItem(discount)
    }.plus(NavigationItem(page) {
        page = it
        viewModel.loadDataByPage(page)
    }).toMutableList()

}
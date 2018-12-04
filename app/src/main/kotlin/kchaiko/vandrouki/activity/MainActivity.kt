package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.RecyclerAdapter
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.items.BaseRecyclerItem
import kchaiko.vandrouki.items.DiscountItem
import kchaiko.vandrouki.items.NavigationItem
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by inject()
    private var page = DEFAULT_PAGE
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RecyclerAdapter()
        am_discount_list.adapter = adapter
        viewModel.loadingDelegate { showLoadingIndicator(it) }
                .dataDelegate {
                    am_discount_list.scrollToPosition(0)
                    adapter.addItems(getShowingItems(it))
                }
                .errorDelegate { proceedError(it) }
                .provideData()
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        am_discount_list.visibility = if (showLoading) View.GONE else View.VISIBLE
        am_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun getShowingItems(discountList: List<Discount>): MutableList<BaseRecyclerItem> = discountList.map { discount ->
        DiscountItem(discount) {
            viewModel.setCurrentDiscount(it)
            startActivity(DiscountActivity.getIntent(this@MainActivity))
        } as BaseRecyclerItem
    }.plus(NavigationItem(page) {
        page = it
        viewModel.loadDataByPage(page)
    }).toMutableList()

}

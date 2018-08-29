package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.items.BaseRecyclerItem
import kchaiko.vandrouki.items.DiscountItem
import kchaiko.vandrouki.items.NavigationItem
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by inject()
    private val binding: ActivityMainBinding by inject { mapOf("activity" to this) }
    private var page = DEFAULT_PAGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingDelegate { binding.isLoading = it }
                .dataDelegate {
                    binding.rvDiscounts.scrollToPosition(0)
                    binding.adapter?.addItems(getShowingItems(it))
                }
                .errorDelegate { proceedError(it) }
                .provideData()
    }

    private fun getShowingItems(discountList: List<Discount>) = discountList.map { discount ->
        DiscountItem(discount) {
            viewModel.setCurrentDiscount(it)
            startActivity(DiscountActivity.getIntent(this@MainActivity))
        } as BaseRecyclerItem
    }.plus(NavigationItem(page) {
        page = it
        viewModel.loadDataByPage(page)
    })

}

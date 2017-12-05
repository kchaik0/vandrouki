package kchaiko.vandrouki.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_recycler.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.discountListLiveData.observe(this, Observer {
            when (it?.status) {
                RequestStatus.SUCCESS -> {
                    am_recycler.adapter = it.data?.let { data -> DiscountAdapter(data, { startActivity(DiscountActivity.getIntent(this, it)) }) }
                    showLoadingIndicator(false)
                }
                RequestStatus.ERROR -> it.exception?.let { exception -> proceedError(exception) }
                RequestStatus.LOADING -> showLoadingIndicator(true)
            }
        })
        viewModel.loadDiscounts()
    }

    private fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

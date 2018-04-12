package kchaiko.vandrouki.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.discountListLiveData.observe(this, Observer {
            when (it?.status) {
                RequestStatus.SUCCESS -> {
                    am_recycler.adapter = it.data?.let { data -> DiscountAdapter(data, { startActivity(DiscountActivity.getIntent(this, it)) }) }
                    showLoadingIndicator(false)
                }
                RequestStatus.ERROR -> {
                    showLoadingIndicator(false)
                    it.exception?.let { exception -> proceedError(exception) }
                }
                RequestStatus.LOADING -> showLoadingIndicator(true)
            }
        })
    }

    private fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

package kchaiko.vandrouki.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.extensions.getColorFromAttribute
import kchaiko.vandrouki.viewmodel.MainViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import kotlin.properties.Delegates

class MainActivity : BaseActivity() {

    private var rvList: RecyclerView by Delegates.notNull()
    private var pbLoading: ProgressBar by Delegates.notNull()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initViewModel()
        viewModel.loadDiscounts()
    }

    private fun initViews() {
        frameLayout {
            lparams(width = matchParent, height = matchParent)
            backgroundColor = getColorFromAttribute(R.attr.colorBackground)
            recyclerView {
                rvList = this
                verticalPadding = dip(4)
                layoutManager = LinearLayoutManager(this@MainActivity)
            }.lparams(width = matchParent, height = matchParent)
            progressBar {
                pbLoading = this
            }.lparams(width = wrapContent, height = wrapContent, gravity = Gravity.CENTER)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.discountListLiveData.observe(this, Observer {
            when (it?.status) {
                RequestStatus.SUCCESS -> {
                    rvList.adapter = it.data?.let { data -> DiscountAdapter(data, { startActivity(DiscountActivity.getIntent(this, it)) }) }
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
        rvList.visibility = if (show) View.GONE else View.VISIBLE
        pbLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

}

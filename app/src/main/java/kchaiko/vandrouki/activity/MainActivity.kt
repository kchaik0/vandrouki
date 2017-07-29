package kchaiko.vandrouki.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.arch.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_recycler.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        showLoadingIndicator(true)
        viewModel.discountBeanList.observe(this, Observer {
            am_recycler.adapter = DiscountAdapter(it!!) {
                startActivity(DiscountActivity.getIntent(this, it))
            }
            showLoadingIndicator(false)
        })
    }

    private fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

package kchaiko.vandrouki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.functions.Consumer
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.network.LoadUrlManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_recycler.layoutManager = LinearLayoutManager(this)
        showLoadingIndicator(true)
        LoadUrlManager.getDiscountBeanList(
                Consumer<MutableList<DiscountBean>> {
                    am_recycler.adapter = DiscountAdapter(it)
                    showLoadingIndicator(false)
                },
                Consumer {  }
        )
    }

    private fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

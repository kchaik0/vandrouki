package kchaiko.vandrouki.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.DiscountBeanList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var disposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_recycler.layoutManager = LinearLayoutManager(this)
        disposable = CompositeDisposable()
        disposable?.add(VandroukiApp.bus.asFlowable().subscribe({
            am_recycler.adapter = DiscountAdapter((it as DiscountBeanList).discountBeanList)
            showLoadingIndicator(false)
        }))
        showLoadingIndicator(true)
        am_click.setOnClickListener({ startActivity(Intent(this, DiscountActivity::class.java)) })
    }

    fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

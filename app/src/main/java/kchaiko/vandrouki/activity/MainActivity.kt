package kchaiko.vandrouki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.network.LoadUrlService
import kchaiko.vandrouki.network.RequestManager
import kchaiko.vandrouki.parsers.HtmlParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        am_recycler.layoutManager = LinearLayoutManager(this)
        showLoadingIndicator(true)
        val retrofit = RequestManager.init()
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        val flowable = loadUrlService.html
        flowable.map<MutableList<DiscountBean>> {
            HtmlParser().parse(it.string())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    am_recycler.adapter = DiscountAdapter(it)
                    showLoadingIndicator(false)
                }
    }

    fun showLoadingIndicator(show: Boolean) {
        am_recycler.visibility = if (show) View.GONE else View.VISIBLE
        am_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

}

package kchaiko.vandrouki.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.network.LoadUrlService
import kchaiko.vandrouki.network.RequestManager
import kchaiko.vandrouki.parsers.HtmlParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RequestManager.init()
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        val flowable = loadUrlService.html
        flowable.map<List<DiscountBean>> {
            HtmlParser().parse(it.string())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { this.am_text.text = it.size.toString() }
    }

}

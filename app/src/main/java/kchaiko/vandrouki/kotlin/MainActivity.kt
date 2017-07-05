package kchaiko.vandrouki.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RequestManager.init()
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        val flowable = loadUrlService.html
        flowable.map<List<DiscountBean>> {
            var document: Document? = null
            try {
                document = Jsoup.parse(it.string())
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val discountElements = document!!.getElementById("primary")
                    .getElementsByAttributeValueContaining("id", "post-")
            val discountBeanList = ArrayList<DiscountBean>()
            for (elem in discountElements) {
                val bean = DiscountBean()
                bean.title = elem.getElementsByAttributeValue("rel", "bookmark").attr("title")
                bean.date = elem.getElementsByClass("published").text()
                discountBeanList.add(bean)
            }
            discountBeanList
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { this.am_text.text = it.size.toString() }
    }

}

package kchaiko.vandrouki

import android.os.AsyncTask
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
 * Task for load data
 *
 * Created by kchaiko on 03.07.2017.
 */
class LoadUrlTask : AsyncTask<String, Void, List<DiscountBean>>() {

    override fun doInBackground(vararg p0: String?): List<DiscountBean>? {
        val document = Jsoup.connect(p0[0]).get()
        val discountElements = document.getElementById("primary")
                .getElementsByAttributeValueContaining("id", "post-")
        val discountBeanList = ArrayList<DiscountBean>()
        discountElements.forEach({ elem: Element ->
            val bean = DiscountBean()
            bean.title = elem.getElementsByAttributeValue("rel", "bookmark").attr("title")
            bean.date = elem.getElementsByClass("published").text()
            discountBeanList.add(bean)
        })
        return discountBeanList
    }

    override fun onPostExecute(result: List<DiscountBean>?) {
        super.onPostExecute(result)
    }
}
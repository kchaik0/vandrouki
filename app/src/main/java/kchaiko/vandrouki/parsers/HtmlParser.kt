package kchaiko.vandrouki.parsers

import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.enumes.DateFormats
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Parser for parse html in data list
 *
 * Created by kchaiko on 05.07.2017.
 */
class HtmlParser {

    fun parse(html: String): MutableList<DiscountBean> {
        var document: Document? = null
        try {
            document = Jsoup.parse(html)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val discountElements = document!!.getElementById("primary")
                .getElementsByAttributeValueContaining("id", "post-")
        val discountBeanList = ArrayList<DiscountBean>()
        val dateFormat = SimpleDateFormat(DateFormats.HTML_FORMAT.format, Locale.getDefault())
        for (elem in discountElements) {
            val bean = DiscountBean()
            bean.title = elem.getElementsByAttributeValue("rel", "bookmark").text()
            bean.date = dateFormat.parse(elem.getElementsByClass("published").text())
            discountBeanList.add(bean)
        }
        return discountBeanList
    }

}
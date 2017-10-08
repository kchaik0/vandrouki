package kchaiko.vandrouki.parsers

import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.enumes.DateFormats
import kchaiko.vandrouki.enumes.discount.Type
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Parser for parse html in data list
 *
 * Created by kchaiko on 05.07.2017.
 */
object HtmlParser {

    fun parse(html: String): List<DiscountBean> {
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
        var title: String
        var date: Date
        var categoryList: List<String>
        for (elem in discountElements) {
            title = elem.getElementsByAttributeValue("rel", "bookmark").text()
            date = dateFormat.parse(elem.getElementsByClass("published").text())
            categoryList = parseCategoryList(elem)
            discountBeanList.add(DiscountBean(title, date, categoryList, getType(categoryList)))
        }
        return discountBeanList
    }

    private fun parseCategoryList(elem: Element): List<String> {
        return elem.attr("class").split(" ").filter { it.startsWith("category") }
    }

    private fun getType(categoryList: List<String>): Type {
        return if (categoryList.any { it.startsWith("category-letim") }) Type.FLY else Type.OTHER
    }

}
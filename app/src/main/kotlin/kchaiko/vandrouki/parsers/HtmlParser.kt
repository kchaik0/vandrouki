package kchaiko.vandrouki.parsers

import kchaiko.vandrouki.beans.Discount
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
class HtmlParser {

    fun parse(html: String): List<Discount> {
        var document: Document? = null
        try {
            document = Jsoup.parse(html)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val discountElements = document!!.getElementById("primary")
                .getElementsByAttributeValueContaining("id", "post-")
        val discountBeanList = ArrayList<Discount>()
        val dateFormat = SimpleDateFormat(DateFormats.HTML_FORMAT.format, Locale.getDefault())
        var title: String
        var date: Date
        var categoryList: List<String>
        for (elem in discountElements) {
            title = elem.getElementsByAttributeValue("rel", "bookmark").text().trim()
            date = dateFormat.parse(elem.getElementsByClass("published").text())
            categoryList = parseCategoryList(elem)
            discountBeanList.add(Discount(title, date, categoryList, getType(categoryList),
                    getImageUrl(elem), getDiscountDesc(elem)))
        }
        return discountBeanList
    }

    private fun parseCategoryList(elem: Element): List<String> {
        return elem.attr("class").split(" ").filter { it.startsWith("category") }
    }

    private fun getType(categoryList: List<String>): Type {
        return if (categoryList.any { it.startsWith("category-letim") }) Type.FLY else Type.OTHER
    }

    private fun getImageUrl(elem: Element) = elem.getElementsByClass("post-thumb")[0].getElementsByTag("img")[0].attr("src")

    private fun getDiscountDesc(elem: Element) = elem.getElementsByClass("entry-content")[0].text()

}
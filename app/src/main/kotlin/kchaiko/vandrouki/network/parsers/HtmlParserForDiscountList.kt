package kchaiko.vandrouki.network.parsers

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.enumes.DateFormats
import kchaiko.vandrouki.enumes.discount.Type
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*

/**
 * Parser for parse html in data list
 *
 * Created by kchaiko on 05.07.2017.
 */
class HtmlParserForDiscountList : BaseHtmlParser<List<Discount>>() {

    companion object {
        fun newInstance() = HtmlParserForDiscountList()
    }

    override fun parse(html: String): List<Discount> {
        val discountBeanList = arrayListOf<Discount>()
        val document = createDocument(html) ?: return discountBeanList
        val discountElements = document.getElementById("primary")
                .getElementsByAttributeValueContaining("id", "post-")
        val dateFormat = SimpleDateFormat(DateFormats.HTML_FORMAT.format, Locale.getDefault())
        var title: String
        var date: Date
        var categoryList: List<String>
        var author: String
        var detailUrl: String
        for (elem in discountElements) {
            title = elem.getElementsByAttributeValue("rel", "bookmark").text().trim()
            date = dateFormat.parse(elem.getElementsByClass("published").text())
            categoryList = parseCategoryList(elem)
            author = elem.getElementsByClass("author")[0].getElementsByTag("a").text()
            detailUrl = getDetailUrl(elem)
            discountBeanList.add(Discount(author, date, categoryList, getType(categoryList), getImageUrl(elem), title, getDiscountDesc(elem), detailUrl))
        }
        return discountBeanList
    }

    private fun parseCategoryList(elem: Element): List<String> = elem.attr("class").split(" ").filter { it.startsWith("category") }

    private fun getType(categoryList: List<String>): Type {
        return if (categoryList.any { it.startsWith("category-letim") }) Type.FLY else Type.OTHER
    }

    private fun getImageUrl(elem: Element) = elem.getElementsByClass("post-thumb")[0].getElementsByTag("img")[0].attr("src")

    private fun getDetailUrl(elem: Element) = elem.getElementsByClass("post-thumb")[0].getElementsByTag("a")[0].attr("href")

    private fun getDiscountDesc(elem: Element) = elem.getElementsByClass("entry-content")[0].getElementsByTag("p")[0].text()

}
package kchaiko.vandrouki.network.parsers

import kchaiko.vandrouki.beans.DetailedDiscount

/**
 * Parser for parse html in data list
 *
 * Created by kchaiko on 05.07.2017.
 */
class HtmlParserForDetailedDiscount: BaseHtmlParser<DetailedDiscount?>() {

    companion object {
        fun newInstance() = HtmlParserForDetailedDiscount()
    }

    override fun parse(html: String): DetailedDiscount? {
        val document = createDocument(html) ?: return null
        return DetailedDiscount(document.getElementsByClass("entry-content").text())
    }
}
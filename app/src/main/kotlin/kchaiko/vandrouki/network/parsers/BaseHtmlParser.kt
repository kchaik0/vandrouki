package kchaiko.vandrouki.network.parsers

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

abstract class BaseHtmlParser<R> : HtmlParser<R> {

    fun createDocument(html: String): Document? {
        var document: Document? = null
        try {
            document = Jsoup.parse(html)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return document
    }

}
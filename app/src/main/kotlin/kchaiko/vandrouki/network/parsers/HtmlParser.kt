package kchaiko.vandrouki.network.parsers

interface HtmlParser<R> {

    fun parse(html: String): R

}
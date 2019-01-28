package kchaiko.vandrouki.network.converter

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.network.parsers.BaseHtmlParser
import kchaiko.vandrouki.network.parsers.HtmlParserForDetailedDiscount
import kchaiko.vandrouki.network.parsers.HtmlParserForDiscountList
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class HtmlConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?):
            Converter<ResponseBody, *>? {
        val parser = when (type) {
            DetailedDiscount::class.java -> HtmlParserForDetailedDiscount.newInstance()
            DiscountList::class.java -> HtmlParserForDiscountList.newInstance()
            else -> throw VandException("Must have parsers for all types")
        }
        return HtmlConverter(parser)
    }

    private class HtmlConverter<RES>(private val parser: BaseHtmlParser<RES>) : Converter<ResponseBody, RES> {
        override fun convert(value: ResponseBody): RES? = parser.parse(value.string())
    }

}
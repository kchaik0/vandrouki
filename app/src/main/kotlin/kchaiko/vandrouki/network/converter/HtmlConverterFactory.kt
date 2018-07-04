package kchaiko.vandrouki.network.converter

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.network.parsers.HtmlParserForDetailedDiscount
import kchaiko.vandrouki.network.parsers.HtmlParserForDiscountList
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class HtmlConverterForDiscountList : Converter<ResponseBody, DiscountList> {

    private val parser = HtmlParserForDiscountList.newInstance()

    override fun convert(value: ResponseBody) = parser.parse(value.string())
}

class HtmlConverterForDetailedDiscount : Converter<ResponseBody, DetailedDiscount> {

    private val parser = HtmlParserForDetailedDiscount.newInstance()

    override fun convert(value: ResponseBody) = parser.parse(value.string())

}

class HtmlConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?):
            Converter<ResponseBody, *>? {
        return when (type) {
            DetailedDiscount::class.java -> HtmlConverterForDetailedDiscount()
            DiscountList::class.java -> HtmlConverterForDiscountList()
            else -> null
        }
    }
}
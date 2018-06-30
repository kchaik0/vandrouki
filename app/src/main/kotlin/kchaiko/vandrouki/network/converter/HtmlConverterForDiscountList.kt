package kchaiko.vandrouki.network.converter

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.network.parsers.HtmlParserForDiscountList
import okhttp3.ResponseBody
import retrofit2.Converter

class HtmlConverterForDiscountList : Converter<ResponseBody, List<Discount>> {

    private val parser = HtmlParserForDiscountList.newInstance()

    override fun convert(value: ResponseBody) = parser.parse(value.string())
}
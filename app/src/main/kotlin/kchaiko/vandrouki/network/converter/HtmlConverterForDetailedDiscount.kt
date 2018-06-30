package kchaiko.vandrouki.network.converter

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.network.parsers.HtmlParserForDetailedDiscount
import okhttp3.ResponseBody
import retrofit2.Converter

class HtmlConverterForDetailedDiscount : Converter<ResponseBody, DetailedDiscount> {

    private val parser = HtmlParserForDetailedDiscount.newInstance()

    override fun convert(value: ResponseBody) = parser.parse(value.string())

}
package kchaiko.vandrouki.network.converter

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.network.parsers.HtmlParser
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class HtmlConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return HtmlConverter()
    }

    class HtmlConverter : Converter<ResponseBody, List<Discount>> {

        private val parser = HtmlParser.newInstance()

        override fun convert(value: ResponseBody?) = parser.parse(value?.string())
    }

}
package kchaiko.vandrouki.network.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.network.converter.HtmlConverterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

const val SITE_URL = "https://vandrouki.by/"

interface LoadUrlService {

    @get:GET("/")
    val htmlDiscountList: Deferred<DiscountList>

    @GET("/{urlPart}")
    fun htmlDetailedDiscount(@Path("urlPart") urlPart: String): Deferred<DetailedDiscount>

    companion object Factory {

        fun create(): LoadUrlService = Retrofit.Builder()
                .baseUrl(SITE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(HtmlConverterFactory())
                .build()
                .create(LoadUrlService::class.java)

    }

}

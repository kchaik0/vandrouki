package kchaiko.vandrouki.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kchaiko.vandrouki.network.converter.HtmlConverterFactory
import kchaiko.vandrouki.network.converter.HtmlConverterForDetailedDiscount
import kchaiko.vandrouki.network.converter.HtmlConverterForDiscountList
import kchaiko.vandrouki.network.service.LoadUrlService
import retrofit2.Retrofit

object RetrofitManager {

    fun getDiscountListApiService(): LoadUrlService = Retrofit.Builder()
            .baseUrl("https://vandrouki.by")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(HtmlConverterFactory(HtmlConverterForDiscountList()))
            .build()
            .create(LoadUrlService::class.java)

    fun getDiscountDetailedApiService(loadApiUrl: String): LoadUrlService = Retrofit.Builder()
            .baseUrl(loadApiUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(HtmlConverterFactory(HtmlConverterForDetailedDiscount()))
            .build()
            .create(LoadUrlService::class.java)

}
package kchaiko.vandrouki.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kchaiko.vandrouki.network.converter.HtmlConverterFactory
import kchaiko.vandrouki.network.service.SITE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitManager {

    private val networkClient: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(SITE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(HtmlConverterFactory())
            .client(networkClient)
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}
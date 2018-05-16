package kchaiko.vandrouki.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kchaiko.vandrouki.network.service.LoadUrlService
import retrofit2.Retrofit

object RetrofitManager {

    fun getApiService(): LoadUrlService = Retrofit.Builder()
            .baseUrl("https://vandrouki.by")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(LoadUrlService::class.java)

}
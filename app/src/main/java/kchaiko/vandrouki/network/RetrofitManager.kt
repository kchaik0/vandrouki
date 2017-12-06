package kchaiko.vandrouki.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Manager for request
 *
 * Created by kchaiko on 07.07.2017.
 */
object RetrofitManager {

    private val BASE_URL = "https://vandrouki.by"

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build()

}
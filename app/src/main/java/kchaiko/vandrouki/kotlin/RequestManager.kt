package kchaiko.vandrouki.kotlin

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit

/**
 * Manager for request

 * Created by kchaiko on 05.07.2017.
 */

object RequestManager {

    private val BASE_URL = "https://vandrouki.by"

    fun init(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}

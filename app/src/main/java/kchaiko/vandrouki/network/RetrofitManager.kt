package kchaiko.vandrouki.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit

/**
 * Manager for request

 * Created by kchaiko on 05.07.2017.
 */

class RetrofitManager private constructor() {

    private object Holder {

        private val BASE_URL = "https://vandrouki.by"

        val instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    companion object {
        val retrofit: Retrofit by lazy { Holder.instance }
    }

}

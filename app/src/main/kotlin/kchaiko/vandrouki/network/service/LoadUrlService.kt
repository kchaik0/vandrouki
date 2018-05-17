package kchaiko.vandrouki.network.service

import kchaiko.vandrouki.beans.Discount
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val html: Deferred<List<Discount>>

}

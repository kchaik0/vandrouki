package kchaiko.vandrouki.network.service

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val html: Deferred<ResponseBody>

}

package kchaiko.vandrouki.network.service

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val html: Single<ResponseBody>

}

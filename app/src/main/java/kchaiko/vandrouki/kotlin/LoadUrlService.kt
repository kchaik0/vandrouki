package kchaiko.vandrouki.kotlin

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val html: Flowable<ResponseBody>

}

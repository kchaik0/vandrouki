package kchaiko.vandrouki.network

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val html: Flowable<ResponseBody>

}

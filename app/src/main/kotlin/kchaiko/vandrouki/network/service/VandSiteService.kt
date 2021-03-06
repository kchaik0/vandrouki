package kchaiko.vandrouki.network.service

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

const val SITE_URL = "https://vandrouki.by/"
const val DEFAULT_PAGE = 1

interface VandSiteService {

    @GET("/page/{page}")
    fun htmlDiscountList(@Path("page") page: Int): Deferred<DiscountList>

    @GET("/{urlPart}")
    fun htmlDetailedDiscount(@Path("urlPart") urlPart: String): Deferred<DetailedDiscount>

}

package kchaiko.vandrouki.network.service

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface LoadUrlService {

    @get:GET("/")
    val htmlDiscountList: Deferred<List<Discount>>

    @get:GET("/")
    val htmlDetailedDiscount: Deferred<DetailedDiscount>

}

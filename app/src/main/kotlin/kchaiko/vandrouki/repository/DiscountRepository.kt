package kchaiko.vandrouki.repository

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.parsers.HtmlParser

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
object DiscountRepository {

    suspend fun loadDiscountList(): Resource<List<Discount>> {
        return try {
            val discountList = RetrofitManager.getApiService().html.await()
            Resource.success(discountList)
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}

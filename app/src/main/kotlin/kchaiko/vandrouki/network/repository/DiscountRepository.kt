package kchaiko.vandrouki.network.repository

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.VandSiteService

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository(retrofitManager: RetrofitManager) : BaseRepository() {

    private val siteService = retrofitManager.create(VandSiteService::class.java)

    suspend fun loadDiscountList() = awaitWithException(siteService.htmlDiscountList())

    suspend fun loadDiscountsByPage(page: Int): Resource<DiscountList> {
        val deferred = siteService.htmlDiscountList(page)
        return awaitWithException(deferred)
    }

    suspend fun loadDetailedDiscount(detailedUrl: String): Resource<DetailedDiscount> {
        val deferred = siteService.htmlDetailedDiscount(detailedUrl)
        return awaitWithException(deferred)
    }
}

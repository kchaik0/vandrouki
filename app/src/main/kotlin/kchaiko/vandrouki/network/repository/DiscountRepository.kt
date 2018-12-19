package kchaiko.vandrouki.network.repository

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.VandSiteService
import kotlinx.coroutines.Deferred

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository(retrofitManager: RetrofitManager) : BaseRepository() {

    private val siteService = retrofitManager.create(VandSiteService::class.java)

    private lateinit var discountListTask: Deferred<DiscountList>

    fun loadDiscountList() {
        discountListTask = siteService.htmlDiscountList()
    }

    suspend fun getDataResource(): Resource<DiscountList> = awaitWithException(discountListTask)

    suspend fun loadDiscountsByPage(page: Int): Resource<DiscountList> {
        val deferred = siteService.htmlDiscountList(page)
        return awaitWithException(deferred)
    }

    suspend fun loadDetailedDiscount(detailedUrl: String): Resource<DetailedDiscount> {
        val deferred = siteService.htmlDetailedDiscount(detailedUrl)
        return awaitWithException(deferred)
    }
}

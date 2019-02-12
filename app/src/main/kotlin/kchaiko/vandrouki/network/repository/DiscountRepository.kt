package kchaiko.vandrouki.network.repository

import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.VandSiteService

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository(retrofitManager: RetrofitManager) : BaseRepository() {

    private val siteService = retrofitManager.create(VandSiteService::class.java)

    suspend fun loadDiscountsByPage(page: Int) = awaitWithException(siteService.htmlDiscountList(page))

    suspend fun loadDetailedDiscount(detailedUrl: String) = awaitWithException(siteService.htmlDetailedDiscount(detailedUrl))

}

package kchaiko.vandrouki.repository

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.service.LoadUrlService
import kotlinx.coroutines.experimental.Deferred

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository : Repository {

    private lateinit var discountListTask: Deferred<DiscountList>

    fun loadDiscountList() {
        discountListTask = LoadUrlService.create().htmlDiscountList
    }

    suspend fun getDataResource(): Resource<DiscountList> {
        return try {
            Resource.success(discountListTask.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }

    suspend fun loadDetailedDiscount(detailedUrl: String): Resource<DetailedDiscount> {
        val deferred = LoadUrlService.create().htmlDetailedDiscount(detailedUrl)
        return try {
            Resource.success(deferred.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}

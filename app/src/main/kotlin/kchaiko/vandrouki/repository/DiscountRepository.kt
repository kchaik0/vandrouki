package kchaiko.vandrouki.repository

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.RetrofitManager
import kotlinx.coroutines.experimental.Deferred

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository : Repository {

    private lateinit var discountListTask: Deferred<List<Discount>>

    fun loadDiscountList() {
        discountListTask = RetrofitManager.getDiscountListApiService().htmlDiscountList
    }

    suspend fun getDataResource(): Resource<List<Discount>> {
        return try {
            Resource.success(discountListTask.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }

    suspend fun loadDetailedDiscount(detailedUrl: String): Resource<DetailedDiscount> {
        val deferred = RetrofitManager.getDiscountDetailedApiService(detailedUrl).htmlDetailedDiscount
        return try {
            Resource.success(deferred.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}

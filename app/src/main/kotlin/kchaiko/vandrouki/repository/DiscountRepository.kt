package kchaiko.vandrouki.repository

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

    private lateinit var task: Deferred<List<Discount>>

    fun loadDiscountList() {
        task = RetrofitManager.getApiService().html
    }

    suspend fun getDataResource(): Resource<List<Discount>> {
        return try {
            Resource.success(task.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}

package kchaiko.vandrouki.repository

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.converter.HtmlConverterFactory
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.network.service.SITE_URL
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository(retrofit: Retrofit) : Repository {

    private val loadUrlService = retrofit.create(LoadUrlService::class.java)

    private lateinit var discountListTask: Deferred<DiscountList>

    fun loadDiscountList() {
        discountListTask = loadUrlService.htmlDiscountList()
    }

    suspend fun getDataResource(): Resource<DiscountList> {
        return try {
            Resource.success(discountListTask.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }

    suspend fun loadDiscountsByPage(page: Int): Resource<DiscountList> {
        val deferred = loadUrlService.htmlDiscountList(page)
        return try {
            Resource.success(deferred.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }

    suspend fun loadDetailedDiscount(detailedUrl: String): Resource<DetailedDiscount> {
        val deferred = loadUrlService.htmlDetailedDiscount(detailedUrl)
        return try {
            Resource.success(deferred.await())
        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}

fun initRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(SITE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(HtmlConverterFactory())
        .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
        .build()

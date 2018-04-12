package kchaiko.vandrouki.repository

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.parsers.HtmlParser
import javax.inject.Inject

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountRepository(private val htmlParser: HtmlParser) {

    private val loadUrlService = RetrofitManager.retrofit.create(LoadUrlService::class.java)
    val discountListLiveData = MutableLiveData<Resource<List<Discount>>>()

    fun loadDiscountList() {
        discountListLiveData.value = Resource.loading()
        loadUrlService.html.map({ htmlParser.parse(it.string()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            discountListLiveData.value = Resource.success(it)
                        },
                        onError = {
                            discountListLiveData.value = Resource.error(it)
                        })
    }
}

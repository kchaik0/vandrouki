package kchaiko.vandrouki.repository

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.parsers.HtmlParser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
@Singleton
class DiscountRepository @Inject constructor(private val apiService: LoadUrlService, private val htmlParser: HtmlParser) {

    val discountListLiveData = MutableLiveData<Resource<List<Discount>>>()

    fun loadDiscountList() {
        discountListLiveData.value = Resource.loading()
        apiService.html.map({ htmlParser.parse(it.string()) })
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

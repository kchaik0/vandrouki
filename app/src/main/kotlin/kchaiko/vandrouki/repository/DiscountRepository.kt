package kchaiko.vandrouki.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.parsers.HtmlParser
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager for subscribe url request
 *
 * Created by kchaiko on 06.07.2017.
 */
@Singleton
class DiscountRepository @Inject constructor(private val apiService: LoadUrlService, private val htmlParser: HtmlParser) {

    lateinit var discountListSubject: BehaviorSubject<Resource<List<Discount>>>

    fun loadDiscountList() {
        discountListSubject = BehaviorSubject.createDefault(Resource.loading())
        apiService.html.map({ htmlParser.parse(it.string()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            discountListSubject.onNext(discountListSubject.value.setStatusSuccess(it))
                        },
                        onError = {
                            discountListSubject.onNext(discountListSubject.value.setStatusError(it))
                        })
    }
}

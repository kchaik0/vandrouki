package kchaiko.vandrouki.network.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.parsers.HtmlParser

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
object DiscountRepository {

    private val loadUrlService = RetrofitManager.retrofit.create(LoadUrlService::class.java)

    fun getDiscountList(): Single<List<Discount>> {
        return loadUrlService.html.map<List<Discount>>({ HtmlParser.parse(it.string()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

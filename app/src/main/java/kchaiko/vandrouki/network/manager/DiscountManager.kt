package kchaiko.vandrouki.network.manager

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.ResponseBean
import kchaiko.vandrouki.network.RetrofitManager
import kchaiko.vandrouki.network.service.LoadUrlService
import kchaiko.vandrouki.parsers.HtmlParser

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
object DiscountManager {

    fun getDiscountList(): Single<ResponseBean<List<Discount>>> {
        val retrofit = RetrofitManager.retrofit
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        return loadUrlService.html.map<ResponseBean<List<Discount>>>({ ResponseBean(HtmlParser.parse(it.string())) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

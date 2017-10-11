package kchaiko.vandrouki.network

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.beans.RequestBean
import kchaiko.vandrouki.parsers.HtmlParser

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
object LoadDiscountListManager {

    fun getDiscountBeanList(): Flowable<RequestBean<DiscountBean>> {
        val retrofit = RetrofitManager.retrofit
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        return loadUrlService.html.map<RequestBean<DiscountBean>>({ RequestBean(HtmlParser.parse(it.string())) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}

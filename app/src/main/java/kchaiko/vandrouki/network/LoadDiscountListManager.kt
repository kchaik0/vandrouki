package kchaiko.vandrouki.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.parsers.HtmlParser

/**
 * Manager for load url request
 *
 * Created by kchaiko on 06.07.2017.
 */
object LoadDiscountListManager {

    fun getDiscountBeanList(consumerSuccess: Consumer<List<DiscountBean>>, consumerError: Consumer<Throwable>): Disposable {
        val retrofit = RetrofitManager.retrofit
        val loadUrlService = retrofit.create(LoadUrlService::class.java)
        return loadUrlService.html.map<List<DiscountBean>>({ HtmlParser.parse(it.string()) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumerSuccess, consumerError)
    }

}

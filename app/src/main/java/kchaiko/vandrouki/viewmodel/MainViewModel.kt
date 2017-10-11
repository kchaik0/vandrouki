package kchaiko.vandrouki.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.beans.RequestBean
import kchaiko.vandrouki.network.LoadDiscountListManager

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(application: Application?) : AndroidViewModel(application) {

    val discountList: MutableLiveData<RequestBean<DiscountBean>> by lazy {
        LoadDiscountListManager.getDiscountBeanList().subscribeBy(
                onError = { discountList.value = RequestBean(it.message) },
                onComplete = { },
                onNext = { discountList.value = it })
        MutableLiveData<RequestBean<DiscountBean>>()
    }


}
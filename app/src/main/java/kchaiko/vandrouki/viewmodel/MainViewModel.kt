package kchaiko.vandrouki.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.beans.ResponseBean
import kchaiko.vandrouki.network.manager.DiscountManager

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(application: Application?) : AndroidViewModel(application) {

    lateinit var status: RequestStatus

    val discountList: MutableLiveData<ResponseBean<List<Discount>>> by lazy {
        status = RequestStatus.LOADING
        DiscountManager.getDiscountList().subscribeBy(
                onSuccess = {
                    status = RequestStatus.SUCCESS
                    discountList.value = it
                },
                onError = {
                    status = RequestStatus.ERROR
                    discountList.value = ResponseBean(it.message)
                })
        MutableLiveData<ResponseBean<List<Discount>>>()
    }


}
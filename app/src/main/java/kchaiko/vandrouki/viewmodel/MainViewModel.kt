package kchaiko.vandrouki.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel : ViewModel() {

    val discountListLiveData = MutableLiveData<Resource<List<Discount>>>()

    fun loadDiscounts() {
        discountListLiveData.value = Resource.loading()
        DiscountRepository.getDiscountList().subscribeBy(
                onSuccess = {
                    discountListLiveData.value = Resource.success(it)
                },
                onError = {
                    discountListLiveData.value = Resource.error(it)
                })
    }


}
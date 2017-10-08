package kchaiko.vandrouki.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.functions.Consumer
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.network.LoadDiscountListManager


/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(application: Application?) : AndroidViewModel(application) {

    val discountList: MutableLiveData<List<DiscountBean>> by lazy {
        LoadDiscountListManager.getDiscountBeanList(
                Consumer {
                    discountList.value = it
                },
                Consumer { }
        )
        MutableLiveData<List<DiscountBean>>()
    }


}
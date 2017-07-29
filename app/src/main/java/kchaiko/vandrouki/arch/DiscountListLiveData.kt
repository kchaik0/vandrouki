package kchaiko.vandrouki.arch

import android.arch.lifecycle.LiveData
import io.reactivex.functions.Consumer
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.network.LoadDiscountListManager

/**
 * LiveData for load discount list
 *
 * Created by kchaiko on 29.07.2017.
 */
class DiscountListLiveData : LiveData<MutableList<DiscountBean>>() {

    init {
        LoadDiscountListManager.getDiscountBeanList(
                Consumer {
                    value = it
                },
                Consumer {  }
        )
    }

}
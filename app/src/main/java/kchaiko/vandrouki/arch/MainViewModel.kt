package kchaiko.vandrouki.arch

import android.arch.lifecycle.ViewModel


/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel : ViewModel() {

    val discountBeanList : DiscountListLiveData by lazy { DiscountListLiveData() }

}
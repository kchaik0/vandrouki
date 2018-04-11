package kchaiko.vandrouki.viewmodel

import android.arch.lifecycle.ViewModel
import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel : ViewModel() {

    val discountListLiveData = DiscountRepository.discountListLiveData

}
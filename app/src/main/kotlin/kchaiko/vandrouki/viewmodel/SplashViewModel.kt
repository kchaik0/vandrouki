package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class SplashViewModel(private val discountRepository: DiscountRepository) : BaseViewModel() {

    fun loadData() {
        discountRepository.loadDiscountList()
    }

}
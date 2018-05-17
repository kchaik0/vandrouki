package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class SplashViewModel : BaseViewModel() {

    companion object {
        fun newInstance() = SplashViewModel()
    }

    fun loadData() {
        DiscountRepository.loadDiscountList()
    }

}
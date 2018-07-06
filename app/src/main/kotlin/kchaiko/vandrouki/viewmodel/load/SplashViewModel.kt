package kchaiko.vandrouki.viewmodel.load

import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.viewmodel.BaseViewModel

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class SplashViewModel(private val discountRepository: DiscountRepository) : BaseViewModel(), LoadDataViewModel {

    override fun loadData() {
        discountRepository.loadDiscountList()
    }

}
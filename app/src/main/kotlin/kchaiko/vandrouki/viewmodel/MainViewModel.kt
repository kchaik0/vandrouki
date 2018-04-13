package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.repository.DiscountRepository

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(discountRepository: DiscountRepository) {

    val discountListLiveData = discountRepository.discountListLiveData

}
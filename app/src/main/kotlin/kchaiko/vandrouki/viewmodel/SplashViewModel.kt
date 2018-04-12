package kchaiko.vandrouki.viewmodel

import android.arch.lifecycle.ViewModel
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.repository.DiscountRepository
import javax.inject.Inject

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class SplashViewModel : ViewModel() {

    @Inject
    lateinit var discountRepository: DiscountRepository

    init {
        VandroukiApp.repositoryComponent.inject(this)
    }

    fun loadDiscounts() {
        discountRepository.loadDiscountList()
    }

}
package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.beans.Discount

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel : BaseViewModel() {
    lateinit var discount: Discount

    companion object {
        fun newInstance() = DiscountViewModel()
    }
}
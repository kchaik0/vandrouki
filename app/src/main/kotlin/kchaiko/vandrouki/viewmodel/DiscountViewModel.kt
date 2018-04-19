package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import javax.inject.Inject

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel : BaseViewModel() {

    @Inject
    lateinit var discount: Discount

    init {
        VandroukiApp.INSTANCE.discountComponent?.inject(this)
    }

}
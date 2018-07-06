package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(val discount: Discount, private val discountRepository: DiscountRepository) : DataViewModel<DetailedDiscount>() {

    override fun provideData() = launch(UI) {
        provideLoading(true)
        val dataResource = discountRepository.loadDetailedDiscount(discount.detailUrlPart)
        dataResource.apply { provideResult(this) }
    }

}
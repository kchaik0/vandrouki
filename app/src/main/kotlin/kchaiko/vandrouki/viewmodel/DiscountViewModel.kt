package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
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
class DiscountViewModel(val discount: Discount, private val discountRepository: DiscountRepository) : BaseViewModel() {

    val detailedDiscount: ObservableField<DetailedDiscount> = ObservableField(DetailedDiscount(discount.desc))

    fun provideDetailedDiscount() = launch(UI) {
        //show progress
        println("Load started")
        val dataResource = discountRepository.loadDetailedDiscount(discount.detailUrl)
        //provide data
        dataResource.data?.apply { detailedDiscount.set(this) }
    }

}
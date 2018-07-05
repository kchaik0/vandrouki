package kchaiko.vandrouki.viewmodel

import android.databinding.ObservableField
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(val discount: Discount, private val discountRepository: DiscountRepository) : BaseViewModel() {

    private lateinit var errorDelegate: (VandException) -> Unit

    var isLoading: ObservableField<Boolean> = ObservableField()
    var detailedDiscount: ObservableField<DetailedDiscount> = ObservableField()

    fun provideDetailedDiscount() = launch(UI) {
        provideLoading(true)
        val dataResource = discountRepository.loadDetailedDiscount(discount.detailUrlPart)
        dataResource.apply { provideResult(this) }
    }

    fun errorDelegate(function: (VandException) -> Unit) = apply { errorDelegate = function }

    private fun provideLoading(loading: Boolean = false) {
        isLoading.set(loading)
    }

    private fun provideResult(it: Resource<DetailedDiscount>) {
        when (it.status) {
            RequestStatus.SUCCESS -> {
                it.data?.apply { detailedDiscount.set(this) }
                provideLoading()
            }
            RequestStatus.ERROR -> {
                provideLoading()
                it.exception?.apply { errorDelegate(this) }
            }
            RequestStatus.LOADING -> provideLoading(true)
        }
    }

}
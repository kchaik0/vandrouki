package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountListViewModel(private val discountRepository: DiscountRepository) : DataViewModel<DiscountList>() {

    fun provideData() {
        uiScope.launch {
            provideLoading(true)
            val resource = discountRepository.getDataResource()
            provideResult(resource)
        }
    }

    fun loadDataByPage(page: Int) {
        uiScope.launch {
            provideLoading(true)
            val resource = discountRepository.loadDiscountsByPage(page)
            provideResult(resource)
        }
    }

}
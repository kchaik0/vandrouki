package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.network.repository.DiscountRepository
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kotlinx.coroutines.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountListViewModel(private val discountRepository: DiscountRepository, var page: Int = DEFAULT_PAGE) : BaseViewModel<DiscountList>() {

    fun provideData() {
        if (modelLiveData.value == null && errorLiveData.value == null) {
            uiScope.launch {
                provideLoading(true)
                val resource = discountRepository.loadDiscountList()
                provideResult(resource)
            }
        }
    }

    fun loadDataByPage(page: Int) {
        this.page = page
        uiScope.launch {
            provideLoading(true)
            val resource = discountRepository.loadDiscountsByPage(page)
            provideResult(resource)
        }
    }

}
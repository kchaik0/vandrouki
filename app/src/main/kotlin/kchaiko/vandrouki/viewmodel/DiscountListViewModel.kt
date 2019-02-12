package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.network.repository.DiscountRepository
import kotlinx.coroutines.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountListViewModel(private val discountRepository: DiscountRepository, var page: Int) : BaseViewModel<DiscountList>() {

    init {
        loadDiscountDataByPage()
    }

    fun loadDataByPage(page: Int) {
        this.page = page
        loadDiscountDataByPage()
    }

    private fun loadDiscountDataByPage() {
        uiScope.launch {
            provideLoading(true)
            val resource = discountRepository.loadDiscountsByPage(page)
            provideResult(resource)
        }
    }

}
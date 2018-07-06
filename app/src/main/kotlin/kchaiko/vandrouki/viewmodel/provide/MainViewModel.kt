package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.DiscountList
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class MainViewModel(private val discountRepository: DiscountRepository) : DataViewModel<DiscountList>() {

    override fun provideData() = launch(UI) {
        provideLoading(true)
        val resource = discountRepository.getDataResource()
        provideResult(resource)
    }

}
package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.entity.FavouriteDiscount
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(private val discountRepository: DiscountRepository,
                        private val favouriteRepository: FavouriteRepository) : DataViewModel<DetailedDiscount>() {

    fun provideData(detailUrlPart: String) {
        uiScope.launch {
            provideLoading(true)
            val dataResource = discountRepository.loadDetailedDiscount(detailUrlPart)
            dataResource.apply { provideResult(this) }
        }
    }

    fun getFavourite(detailUrlPart: String) = favouriteRepository.getFavourite(detailUrlPart)

    fun saveData(discount: Discount, checked: Boolean) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                favouriteRepository.saveData(discount, checked)
            }
        }
    }

    fun updateData(favouriteDiscount: FavouriteDiscount) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                favouriteRepository.updateData(favouriteDiscount)
            }
        }
    }

}
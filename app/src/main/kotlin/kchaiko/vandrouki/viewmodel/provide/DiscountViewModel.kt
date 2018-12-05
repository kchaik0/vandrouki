package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.FavouriteManager
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(private val discountRepository: DiscountRepository,
                        private val favouriteManager: FavouriteManager) : DataViewModel<DetailedDiscount>() {

    fun provideData(detailUrlPart: String) {
        uiScope.launch {
            provideLoading(true)
            val dataResource = discountRepository.loadDetailedDiscount(detailUrlPart)
            dataResource.apply { provideResult(this) }
        }
    }

    fun getFavourite(detailUrlPart: String) = favouriteManager.getFavourite(detailUrlPart)?.isFavourite
            ?: false

    fun saveData(discount: Discount, checked: Boolean) {
        favouriteManager.updateFavourite(discount, checked)
    }

}
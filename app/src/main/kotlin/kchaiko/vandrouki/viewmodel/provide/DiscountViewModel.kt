package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.db.FavouriteManager
import kchaiko.vandrouki.repository.DiscountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(private val discountRepository: DiscountRepository,
                        private val favouriteManager: FavouriteManager) : DataViewModel<DetailedDiscount>() {

    val discount = discountRepository.currentDiscount

    override fun provideData() = launch(UI) {
        provideLoading(true)
        val dataResource = discountRepository.loadDetailedDiscount(discount.detailUrlPart)
        dataResource.apply { provideResult(this) }
    }

    fun getFavourite() = favouriteManager.getFavourite(discount.detailUrlPart)?.isFavourite ?: false

    fun saveData(checked: Boolean) {
        favouriteManager.updateFavourite(discount, checked)
    }

}
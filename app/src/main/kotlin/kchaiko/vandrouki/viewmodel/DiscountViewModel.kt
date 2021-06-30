package kchaiko.vandrouki.viewmodel

import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.entity.FavouriteDiscount
import kchaiko.vandrouki.network.repository.DiscountRepository
import kchaiko.vandrouki.network.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * View model class
 *
 * Created by kchaiko on 28.07.2017.
 */
class DiscountViewModel(
    private val discountRepository: DiscountRepository,
    private val favouriteRepository: FavouriteRepository,
    private val detailUrlPart: String
) : BaseViewModel<DetailedDiscount>() {

    init {
        uiScope.launch {
            provideLoading(true)
            val dataResource = discountRepository.loadDetailedDiscount(detailUrlPart)
            dataResource.apply { provideResult(this) }
        }
    }

    fun getFavourite(detailUrlPart: String) = favouriteRepository.getFavourite(detailUrlPart)

    fun updateFavorite(
        favoriteDiscount: FavouriteDiscount?,
        discountModel: Discount,
        isFavorite: Boolean
    ) {
        if (favoriteDiscount == null) {
            saveData(discount = discountModel, checked = isFavorite)
        } else {
            favoriteDiscount.isFavourite = isFavorite
            updateData(favoriteDiscount)
        }
    }

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
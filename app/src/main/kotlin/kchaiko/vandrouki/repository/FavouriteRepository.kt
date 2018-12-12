package kchaiko.vandrouki.repository

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.db.VandDatabase
import kchaiko.vandrouki.db.entity.FavouriteDiscount

class FavouriteRepository(database: VandDatabase) : Repository {

    private val favouriteDAO = database.favouriteDAO()

    fun saveData(discount: Discount, checked: Boolean) {
        val favouriteDiscount = with(discount) {
            FavouriteDiscount(url = detailUrlPart, isFavourite = checked, title = title, desc = desc, image = image)
        }
        favouriteDAO.insert(favouriteDiscount)
    }

    fun updateData(favouriteDiscount: FavouriteDiscount) {
        favouriteDAO.update(favouriteDiscount)
    }

    fun getFavourite(detailUrlPart: String) = favouriteDAO.getFavouriteDiscount(detailUrlPart)

}
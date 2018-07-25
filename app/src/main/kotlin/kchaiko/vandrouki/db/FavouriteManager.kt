package kchaiko.vandrouki.db

import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kchaiko.vandrouki.beans.*

class FavouriteManager(boxStore: BoxStore) {

    private val favouriteBox = boxStore.boxFor<FavouriteDiscount>()

    fun getFavourite(url: String) = favouriteBox.query().equal(FavouriteDiscount_.url, url).build().findFirst()

    fun updateFavourite(discount: Discount, checked: Boolean) {
        var favourite = getFavourite(discount.detailUrlPart)
        if (favourite == null) {
            favourite = favouriteDiscount {
                url = discount.detailUrlPart
                isFavourite = checked
                title = discount.title
                desc = discount.desc
                image = discount.image
            }
        } else {
            favourite = favourite.apply {
                isFavourite = checked
            }
        }
        favouriteBox.put(favourite)
    }

}

fun BoxStore.toManager() = FavouriteManager(this)
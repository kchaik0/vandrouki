package kchaiko.vandrouki.db

import android.content.Context
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import io.objectbox.kotlin.boxFor
import kchaiko.vandrouki.BuildConfig
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

fun Context.initObjectBox() = MyObjectBox.builder().androidContext(this).build().apply {
    if (BuildConfig.DEBUG) {
        AndroidObjectBrowser(this).start(this@initObjectBox)
    }
}.toFavouriteManager()

private fun BoxStore.toFavouriteManager() = FavouriteManager(this)
package kchaiko.vandrouki.beans

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class FavouriteDiscount(@Id var id: Long = 0) {
    var url: String = String()
    var isFavourite: Boolean = false
    var title: String = String()
    var desc: String = String()
    var image: String = String()
}

fun favouriteDiscount(init: FavouriteDiscount.() -> Unit) = FavouriteDiscount().apply(init)
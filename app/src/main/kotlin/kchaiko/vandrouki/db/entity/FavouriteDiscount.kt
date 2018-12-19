package kchaiko.vandrouki.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kchaiko.vandrouki.db.TABLE_FAVOURITE_DISCOUNT

@Entity(tableName = TABLE_FAVOURITE_DISCOUNT)
data class FavouriteDiscount(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                             var url: String = String(),
                             var isFavourite: Boolean = false,
                             var title: String = String(),
                             var desc: String = String(),
                             var image: String = String())
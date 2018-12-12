package kchaiko.vandrouki.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import kchaiko.vandrouki.db.TABLE_FAVOURITE_DISCOUNT

@Entity(tableName = TABLE_FAVOURITE_DISCOUNT)
data class FavouriteDiscount(@PrimaryKey(autoGenerate = true) var id: Long? = null,
                             var url: String = String(),
                             var isFavourite: Boolean = false,
                             var title: String = String(),
                             var desc: String = String(),
                             var image: String = String())
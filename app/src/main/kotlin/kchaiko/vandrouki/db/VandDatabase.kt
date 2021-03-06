package kchaiko.vandrouki.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kchaiko.vandrouki.db.dao.FavouriteDAO
import kchaiko.vandrouki.db.entity.FavouriteDiscount

@Database(entities = [FavouriteDiscount::class], version = 1)
abstract class VandDatabase : RoomDatabase() {

    abstract fun favouriteDAO(): FavouriteDAO

}

const val TABLE_FAVOURITE_DISCOUNT = "favourite_discount"
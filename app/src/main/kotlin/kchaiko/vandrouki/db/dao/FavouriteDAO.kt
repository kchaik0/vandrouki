package kchaiko.vandrouki.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kchaiko.vandrouki.db.TABLE_FAVOURITE_DISCOUNT
import kchaiko.vandrouki.db.entity.FavouriteDiscount

@Suppress("AndroidUnresolvedRoomSqlReference")
@Dao
interface FavouriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favouriteDiscount: FavouriteDiscount)

    @Update
    fun update(favouriteDiscount: FavouriteDiscount)

    @Query("SELECT * FROM $TABLE_FAVOURITE_DISCOUNT WHERE url = :detailedUrlPart")
    fun getFavouriteDiscount(detailedUrlPart: String): LiveData<FavouriteDiscount>

}
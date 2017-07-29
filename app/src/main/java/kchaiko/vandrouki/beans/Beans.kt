package kchaiko.vandrouki.beans

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * File for data class
 *
 * Created by kchaiko on 05.07.2017.
 */

data class DiscountBean(var title: String, var date: Date) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readSerializable() as Date
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeSerializable(date)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<DiscountBean> {
        override fun createFromParcel(parcel: Parcel): DiscountBean {
            return DiscountBean(parcel)
        }

        override fun newArray(size: Int): Array<DiscountBean?> {
            return arrayOfNulls(size)
        }
    }


}
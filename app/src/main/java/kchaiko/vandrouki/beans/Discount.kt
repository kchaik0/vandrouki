package kchaiko.vandrouki.beans

import android.os.Parcel
import android.os.Parcelable
import kchaiko.vandrouki.enumes.discount.Type
import java.util.*

/**
 * Bean for discount
 *
 * Created by kchaiko on 08.10.2017.
 */
data class Discount(val title: String, val date: Date, val categoryList: List<String>, val type: Type,
                    val image: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readSerializable() as Date,
            source.createStringArrayList(),
            Type.values()[source.readInt()],
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeSerializable(date)
        writeStringList(categoryList)
        writeInt(type.ordinal)
        writeString(image)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Discount> = object : Parcelable.Creator<Discount> {
            override fun createFromParcel(source: Parcel): Discount = Discount(source)
            override fun newArray(size: Int): Array<Discount?> = arrayOfNulls(size)
        }
    }
}
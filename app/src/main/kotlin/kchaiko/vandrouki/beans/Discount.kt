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
data class Discount(val author: String, val date: Date, val categoryList: List<String>, val type: Type,
                    val image: String, val title: String, val desc: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readSerializable() as Date,
            source.createStringArrayList(),
            Type.values()[source.readInt()],
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(author)
        writeSerializable(date)
        writeStringList(categoryList)
        writeInt(type.ordinal)
        writeString(image)
        writeString(title)
        writeString(desc)
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<Discount> = object : Parcelable.Creator<Discount> {
            override fun createFromParcel(source: Parcel): Discount = Discount(source)
            override fun newArray(size: Int): Array<Discount?> = arrayOfNulls(size)
        }
    }
}
package kchaiko.vandrouki.beans

import android.os.Parcel
import android.os.Parcelable
import kchaiko.vandrouki.enumes.DateFormats
import kchaiko.vandrouki.enumes.discount.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 * Bean for discount
 *
 * Created by kchaiko on 08.10.2017.
 */
data class Discount(val author: String, val date: Date, val categoryList: List<String>, val type: Type,
                    val image: String, val title: String, val desc: String, val detailUrlPart: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readSerializable() as Date,
            parcel.createStringArrayList()!!,
            Type.values()[parcel.readInt()],
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!)

    fun getDateFormatted(): String = SimpleDateFormat(DateFormats.SHOW_FORMAT.format, Locale.getDefault()).format(date)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeSerializable(date)
        parcel.writeStringList(categoryList)
        parcel.writeInt(type.ordinal)
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeString(detailUrlPart)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Discount> {
        override fun createFromParcel(parcel: Parcel): Discount {
            return Discount(parcel)
        }

        override fun newArray(size: Int): Array<Discount?> {
            return arrayOfNulls(size)
        }
    }

}

class DiscountList : ArrayList<Discount>()
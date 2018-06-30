package kchaiko.vandrouki.beans

import android.os.Parcel
import android.os.Parcelable
import kchaiko.vandrouki.enumes.DateFormats
import kchaiko.vandrouki.enumes.discount.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
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

    fun getDateFormatted(): String = SimpleDateFormat(DateFormats.SHOW_FORMAT.format, Locale.getDefault()).format(date)

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<Discount> = object : Parcelable.Creator<Discount> {
            override fun createFromParcel(source: Parcel): Discount = Discount(source)
            override fun newArray(size: Int): Array<Discount?> = arrayOfNulls(size)
        }
    }
}
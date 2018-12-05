package kchaiko.vandrouki.beans

import android.os.Parcelable
import kchaiko.vandrouki.enumes.DateFormats
import kchaiko.vandrouki.enumes.discount.Type
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

/**
 * Bean for discount
 *
 * Created by kchaiko on 08.10.2017.
 */
@Parcelize
data class Discount(val author: String, val date: Date, val categoryList: List<String>, val type: Type,
                    val image: String, val title: String, val desc: String, val detailUrlPart: String) : Parcelable {

    fun getDateFormatted(): String = SimpleDateFormat(DateFormats.SHOW_FORMAT.format, Locale.getDefault()).format(date)

}

class DiscountList : ArrayList<Discount>()
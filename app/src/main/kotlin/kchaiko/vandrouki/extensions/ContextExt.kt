package kchaiko.vandrouki.extensions

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.util.TypedValue

/**
 * Extension for work with context
 *
 * Created by Chayko_KA on 12.01.2018.
 */

@ColorInt
fun Context.getColorFromAttribute(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}
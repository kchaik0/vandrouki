package kchaiko.vandrouki.extensions

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt

@ColorInt
fun Context.getThemeAttrColor(@AttrRes colorAttr: Int): Int {
    val array = obtainStyledAttributes(null, intArrayOf(colorAttr))
    try {
        return array.getColor(0, 0)
    } finally {
        array.recycle()
    }
}
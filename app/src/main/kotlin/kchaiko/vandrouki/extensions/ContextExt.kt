package kchaiko.vandrouki.extensions

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.widget.ImageView
import com.bumptech.glide.Glide

@ColorInt
fun Context.getThemeAttrColor(@AttrRes colorAttr: Int): Int {
    val array = obtainStyledAttributes(null, intArrayOf(colorAttr))
    try {
        return array.getColor(0, 0)
    } finally {
        array.recycle()
    }
}

fun ImageView.glideLoad(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}
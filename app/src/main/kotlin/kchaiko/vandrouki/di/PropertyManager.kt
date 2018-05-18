package kchaiko.vandrouki.di

import android.content.ComponentCallbacks
import kchaiko.vandrouki.beans.Discount
import org.koin.android.ext.android.setProperty

const val KEY_DISCOUNT = "discount"

fun ComponentCallbacks.setDiscount(discount: Discount) {
    setProperty(KEY_DISCOUNT, discount)
}
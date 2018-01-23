package kchaiko.vandrouki.ui.view

import android.view.ViewManager
import kchaiko.vandrouki.ui.view.divider.DividerView
import kchaiko.vandrouki.ui.view.textview.LargeLightTextView
import kchaiko.vandrouki.ui.view.textview.NormalSecondaryTextView
import org.jetbrains.anko.custom.ankoView

/**
 * Extensions for custom view with anko
 *
 * Created by Chayko_KA on 12.01.2018.
 */

fun ViewManager.divider(init: DividerView.() -> Unit) = ankoView({ DividerView(it) }, theme = 0, init = init)
fun ViewManager.normalSecondaryTextView(init: NormalSecondaryTextView.() -> Unit) = ankoView({ NormalSecondaryTextView(it) }, theme = 0, init = init)
fun ViewManager.largeLightTextView(init: LargeLightTextView.() -> Unit) = ankoView({ LargeLightTextView(it) }, theme = 0, init = init)




package kchaiko.vandrouki.extensions

import android.view.ViewManager
import kchaiko.vandrouki.ui.view.DividerView
import org.jetbrains.anko.custom.ankoView

/**
 * Extensions for custom view with anko
 *
 * Created by Chayko_KA on 12.01.2018.
 */

fun ViewManager.divider(init: DividerView.() -> Unit) = ankoView({ DividerView(it) }, theme = 0, init = init)




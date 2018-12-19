package kchaiko.vandrouki.extensions

import android.view.ViewManager
import android.widget.FrameLayout
import kchaiko.vandrouki.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.frameLayout

inline fun ViewManager.divider(init: FrameLayout.() -> Unit) = frameLayout {
    init.invoke(this)
    backgroundColor = context.getThemeAttrColor(R.attr.colorDivider)
}
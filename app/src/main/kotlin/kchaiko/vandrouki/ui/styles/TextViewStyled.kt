package kchaiko.vandrouki.ui.styles

import android.view.ViewManager
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.getThemeAttrColor
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textView

fun ViewManager.smallSecondaryTextView(init: TextView.() -> Unit) = secondaryTextView {
    init.invoke(this)
    textSize = TEXT_SIZE_SMALL
}

private fun ViewManager.secondaryTextView(init: TextView.() -> Unit) = baseTextView {
    init.invoke(this)
    textColor = context.getThemeAttrColor(R.attr.colorSecondaryText)
}

fun ViewManager.extraLargeDarkTextView(init: TextView.() -> Unit) = darkTextView {
    init.invoke(this)
    textSize = TEXT_SIZE_EXTRA_LARGE
}

private fun ViewManager.darkTextView(init: TextView.() -> Unit) = baseTextView {
    init.invoke(this)
    textColor = context.getThemeAttrColor(R.attr.colorDarkText)
}

private fun ViewManager.baseTextView(init: TextView.() -> Unit) = textView(init)

private const val TEXT_SIZE_SMALL = 11.0f
private const val TEXT_SIZE_EXTRA_LARGE = 20.0f
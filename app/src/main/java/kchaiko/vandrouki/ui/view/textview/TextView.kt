package kchaiko.vandrouki.ui.view.textview

import android.content.Context
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.getColorFromAttribute
import org.jetbrains.anko.textColor

/**
 * Base class for all text view controls
 *
 * Created by kchaiko on 23.01.2018.
 */

open class VandTextView(ctx: Context) : TextView(ctx)

open class SecondaryTextView(ctx: Context) : VandTextView(ctx) {
    init {
        textColor = ctx.getColorFromAttribute(R.attr.colorSecondaryText)
    }
}

class NormalSecondaryTextView(ctx: Context) : SecondaryTextView(ctx) {
    init {
        textSize = 14F
    }
}

open class LightTextView(ctx: Context) : VandTextView(ctx) {
    init {
        textColor = ctx.getColorFromAttribute(R.attr.colorLightText)
    }
}

class LargeLightTextView(ctx: Context) : LightTextView(ctx) {
    init {
        textSize = 18F
    }
}

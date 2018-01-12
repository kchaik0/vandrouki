package kchaiko.vandrouki.ui.view

import android.content.Context
import android.support.annotation.AttrRes
import android.widget.FrameLayout
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.getColorFromAttribute
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent

/**
 * Divider view class
 *
 * Created by Chayko_KA on 12.01.2018.
 */
class DividerView(ctx: Context) : FrameLayout(ctx) {

    var dividerType: DividerType = DividerType.DEFAULT

    init {
        layoutParams = FrameLayout.LayoutParams(matchParent, dip(1))
        backgroundColor = context.getColorFromAttribute(dividerType.bgColor)
    }

}

enum class DividerType(@AttrRes val bgColor: Int) {
    DEFAULT(R.attr.colorDivider),
    LIGHT(R.attr.colorDivider)
}
package kchaiko.vandrouki.ui.item

import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.extensions.getColorFromAttribute
import kchaiko.vandrouki.ui.view.divider
import kchaiko.vandrouki.ui.view.largeLightTextView
import kchaiko.vandrouki.ui.view.normalSecondaryTextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.themedCardView
import kotlin.properties.Delegates

/**
 * Item for showing discount
 *
 * Created by Chayko_KA on 12.01.2018.
 */
class DiscountItem : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        var titleText: TextView by Delegates.notNull()
        var bgImage: ImageView by Delegates.notNull()
        var descText: TextView by Delegates.notNull()
        val itemView = with(ui) {
            themedCardView(R.style.CardView) {
                lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(8)
                    verticalMargin = dip(4)
                }
                cardElevation = px2dip(dip(4))
                radius = px2dip(dip(4))
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL
                    frameLayout {
                        lparams(width = matchParent, height = matchParent)
                        imageView {
                            bgImage = this
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }.lparams(width = matchParent, height = matchParent)
                        largeLightTextView {
                            titleText = this
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                            verticalPadding = dip(8)
                            horizontalPadding = dip(16)
                            backgroundColor = context.getColorFromAttribute(R.attr.colorCardNameBg)
                        }.lparams(width = matchParent, height = wrapContent, gravity = Gravity.BOTTOM)
                    }
                    normalSecondaryTextView {
                        descText = this
                        ellipsize = TextUtils.TruncateAt.END
                        maxLines = 3
                    }.lparams(width = wrapContent, height = wrapContent) {
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                        horizontalMargin = dip(16)
                    }
                    divider {}
                    normalSecondaryTextView {
                        setText(R.string.discount_card_action)
                        allCaps = true
                        padding = dip(8)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        margin = dip(8)
                    }
                }
            }
        }
        itemView.tag = DiscountAdapter.ViewHolder(itemView, titleText, bgImage, descText)
        return itemView
    }
}
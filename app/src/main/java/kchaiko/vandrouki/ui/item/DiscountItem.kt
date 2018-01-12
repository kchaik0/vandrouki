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
import kchaiko.vandrouki.extensions.divider
import kchaiko.vandrouki.extensions.getColorFromAttribute
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
                        themedTextView(theme = R.style.TextViewStyle_Light_Large,
                                text = "asdasdqweqwe qewbhjqhwej qbwjebqwebhj qbwhjebqhwehj bqhwebhjqjbhwe bhqwjbehqjb bhjqwebhjqwbe hbqhwjebhjq bhjqbwehjbqjwe") {
                            titleText = this
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 1
                            verticalPadding = dip(8)
                            horizontalPadding = dip(16)
                            backgroundColor = context.getColorFromAttribute(R.attr.colorCardNameBg)
                        }.lparams(width = matchParent, height = wrapContent, gravity = Gravity.BOTTOM)
                    }
                    themedTextView(theme = R.style.TextViewStyle_Secondary_Normal,
                            text = "asdqweqweqweqweqweqwe qwdasd qweqwe asdqdqweqwe qweqweqwe") {
                        descText = this
                        ellipsize = TextUtils.TruncateAt.END
                        maxLines = 3
                    }.lparams(width = wrapContent, height = wrapContent) {
                        topMargin = dip(16)
                        bottomMargin = dip(16)
                        horizontalMargin = dip(16)
                    }
                    divider { }
                    view {
                        backgroundColor = context.getColorFromAttribute(R.attr.colorDivider)
                    }.lparams(width = matchParent, height = dip(1))
                    themedTextView(theme = R.style.TextViewStyle_Secondary_Normal) {
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
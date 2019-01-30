package kchaiko.vandrouki.ui.component.fragment

import android.graphics.Typeface
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.*
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_AUTHOR
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_CONTENT_CONTAINER
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_CONTENT_DATE
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_FAVOURITE_CHECK
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_FULL_TEXT
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_LOADING
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_LOGO
import kchaiko.vandrouki.ui.ViewIds.DISCOUNT_TITLE
import kchaiko.vandrouki.ui.anko.ConstraintSetBuilder.Side.*
import kchaiko.vandrouki.ui.anko.applyConstraintSet
import kchaiko.vandrouki.ui.fragment.DiscountFragment
import kchaiko.vandrouki.ui.styles.extraLargeDarkTextView
import kchaiko.vandrouki.ui.styles.normalSecondaryTextView
import kchaiko.vandrouki.ui.styles.smallSecondaryTextView
import org.jetbrains.anko.*

class DiscountUI(private val discount: Discount, private val checkedChangeFun: (Boolean) -> Unit) : AnkoComponent<DiscountFragment> {

    override fun createView(ui: AnkoContext<DiscountFragment>): View = with(ui) {
        scrollView {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorDefault)
            isFillViewport = true
            constraintLayoutX {
                imageView {
                    id = DISCOUNT_LOGO
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    glideLoad(discount.image)
                }.lparams(width = MATCH_PARENT, height = dip(320))
                constraintLayoutX {
                    id = DISCOUNT_CONTENT_CONTAINER
                    topPadding = dip(16)
                    horizontalPadding = dip(16)
                    smallSecondaryTextView {
                        id = DISCOUNT_CONTENT_DATE
                        text = discount.getDateFormatted()
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    checkBox {
                        id = DISCOUNT_FAVOURITE_CHECK
                        ui.owner.cbFavourite = this
                        buttonDrawableResource = R.drawable.favourite_selector
                        setOnCheckedChangeListener { _, isChecked ->
                            checkedChangeFun.invoke(isChecked)
                        }
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    smallSecondaryTextView {
                        id = DISCOUNT_AUTHOR
                        text = discount.author
                        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, 0, 0)
                        compoundDrawablePadding = dip(3)
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    extraLargeDarkTextView {
                        id = DISCOUNT_TITLE
                        text = discount.title
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    normalSecondaryTextView {
                        id = DISCOUNT_FULL_TEXT
                        movementMethod = LinkMovementMethod()
                        ui.owner.tvFullText = this
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    progressBar {
                        id = DISCOUNT_LOADING
                        ui.owner.pbLoading = this
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    applyConstraintSet {
                        DISCOUNT_CONTENT_DATE {
                            connect(
                                    TOP to TOP of DISCOUNT_FAVOURITE_CHECK,
                                    BOTTOM to BOTTOM of DISCOUNT_FAVOURITE_CHECK,
                                    START to START of PARENT_ID
                            )
                        }
                        DISCOUNT_FAVOURITE_CHECK {
                            connect(
                                    END to END of PARENT_ID
                            )
                        }
                        DISCOUNT_AUTHOR {
                            connect(
                                    TOP to BOTTOM of DISCOUNT_FAVOURITE_CHECK margin dip(8)
                            )
                        }
                        DISCOUNT_TITLE {
                            connect(
                                    TOP to BOTTOM of DISCOUNT_AUTHOR margin dip(14)
                            )
                        }
                        DISCOUNT_FULL_TEXT {
                            connect(
                                    TOP to BOTTOM of DISCOUNT_TITLE margin dip(10)
                            )
                        }
                        DISCOUNT_LOADING {
                            connect(
                                    TOP to BOTTOM of DISCOUNT_TITLE margin dip(40),
                                    START to START of PARENT_ID,
                                    END to END of PARENT_ID
                            )
                        }
                    }
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
                applyConstraintSet {
                    DISCOUNT_LOGO {
                        connect(
                                TOP to TOP of ConstraintSet.PARENT_ID
                        )
                    }
                    DISCOUNT_CONTENT_CONTAINER {
                        connect(
                                TOP to BOTTOM of DISCOUNT_LOGO
                        )
                    }
                }
            }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
        }
    }

}
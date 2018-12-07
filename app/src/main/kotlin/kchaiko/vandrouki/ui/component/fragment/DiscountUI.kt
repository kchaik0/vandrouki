package kchaiko.vandrouki.ui.component.fragment

import android.graphics.Typeface
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.PARENT_ID
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.extensions.WRAP_CONTENT
import kchaiko.vandrouki.extensions.getThemeAttrColor
import kchaiko.vandrouki.ui.fragment.DiscountFragment
import kchaiko.vandrouki.ui.styles.extraLargeDarkTextView
import kchaiko.vandrouki.ui.styles.normalSecondaryTextView
import kchaiko.vandrouki.ui.styles.smallSecondaryTextView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout

class DiscountUI(private val discount: Discount, private val favouriteChecked: Boolean, private val checkedChangeFun: (Boolean) -> Unit) : AnkoComponent<DiscountFragment> {

    override fun createView(ui: AnkoContext<DiscountFragment>): View = with(ui) {
        scrollView {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorDefault)
            isFillViewport = true
            constraintLayout {
                var ivImageId = 0
                var clContainerId = 0
                imageView {
                    id = View.generateViewId().also { ivImageId = it }
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    Picasso.with(ui.ctx).load(discount.image).into(this)
                }.lparams(width = MATCH_PARENT, height = dip(320))
                constraintLayout {
                    var tvDateId = 0
                    var cbFavouriteId = 0
                    var tvAuthorId = 0
                    var tvTitleId = 0
                    var tvFullTextId = 0
                    var pbLoadingId = 0
                    id = View.generateViewId().also { clContainerId = it }
                    topPadding = dip(16)
                    horizontalPadding = dip(16)
                    smallSecondaryTextView {
                        id = View.generateViewId().also { tvDateId = it }
                        text = discount.getDateFormatted()
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    checkBox {
                        id = View.generateViewId().also { cbFavouriteId = it }
                        buttonDrawableResource = R.drawable.favourite_selector
                        isChecked = favouriteChecked
                        setOnCheckedChangeListener { _, isChecked ->
                            checkedChangeFun.invoke(isChecked)
                        }
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    smallSecondaryTextView {
                        id = View.generateViewId().also { tvAuthorId = it }
                        text = discount.author
                        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, 0, 0)
                        compoundDrawablePadding = dip(3)
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    extraLargeDarkTextView {
                        id = View.generateViewId().also { tvTitleId = it }
                        text = discount.title
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    normalSecondaryTextView {
                        id = View.generateViewId().also { tvFullTextId = it }
                        movementMethod = LinkMovementMethod()
                        ui.owner.tvFullText = this
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    progressBar {
                        id = View.generateViewId().also { pbLoadingId = it }
                        ui.owner.pbLoading = this
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    applyConstraintSet {
                        tvDateId {
                            connect(
                                    TOP to TOP of cbFavouriteId,
                                    BOTTOM to BOTTOM of cbFavouriteId,
                                    START to START of PARENT_ID
                            )
                        }
                        cbFavouriteId {
                            connect(
                                    END to END of PARENT_ID
                            )
                        }
                        tvAuthorId {
                            connect(
                                    TOP to BOTTOM of cbFavouriteId margin dip(8)
                            )
                        }
                        tvTitleId {
                            connect(
                                    TOP to BOTTOM of tvAuthorId margin dip(14)
                            )
                        }
                        tvFullTextId {
                            connect(
                                    TOP to BOTTOM of tvTitleId margin dip(10)
                            )
                        }
                        pbLoadingId {
                            connect(
                                    TOP to BOTTOM of tvTitleId margin dip(40),
                                    START to START of PARENT_ID,
                                    END to END of PARENT_ID
                            )
                        }
                    }
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
                applyConstraintSet {
                    ivImageId {
                        connect(
                                TOP to TOP of ConstraintSet.PARENT_ID
                        )
                    }
                    clContainerId {
                        connect(
                                TOP to BOTTOM of ivImageId
                        )
                    }
                }
            }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
        }
    }

}
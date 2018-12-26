package kchaiko.vandrouki.ui.recycler.holder

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import kchaiko.vandrouki.R
import kchaiko.vandrouki.extensions.*
import kchaiko.vandrouki.ui.anko.ConstraintSetBuilder.Side.*
import kchaiko.vandrouki.ui.anko.applyConstraintSet
import kchaiko.vandrouki.ui.recycler.item.BaseItem
import kchaiko.vandrouki.ui.recycler.item.DiscountItem
import kchaiko.vandrouki.ui.styles.largeLightTextView
import kchaiko.vandrouki.ui.styles.normalSecondaryTextView
import org.jetbrains.anko.*

class DiscountItemViewHolder(context: Context, component: DiscountItemUI) : BaseViewHolder(component.createView(AnkoContext.create(context))) {

    private val ivImage: ImageView = component.ivImage
    private val tvTitle: TextView = component.tvTitle
    private val tvDesc: TextView = component.tvDesc

    override fun bindData(data: BaseItem) {
        if (data is DiscountItem) {
            with(data.discount) {
                ivImage.glideLoad(image)
                tvTitle.text = title
                tvDesc.text = desc
            }
        }
    }
}

class DiscountItemUI : AnkoComponent<Context> {

    lateinit var ivImage: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvDesc: TextView

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        cardViewX {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                margin = dip(8)
            }
            radius = dip(4).toFloat()
            elevation = dip(4).toFloat()
            constraintLayoutX {
                var ivImageId = 0
                var tvTitleId = 0
                var tvDescId = 0
                var dividerId = 0
                var tvReadNextId = 0
                imageView {
                    id = View.generateViewId().also { ivImageId = it }
                    ivImage = this
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams(width = MATCH_PARENT, height = dip(200))
                largeLightTextView {
                    id = View.generateViewId().also { tvTitleId = it }
                    tvTitle = this
                    backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorCardNameBg)
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 1
                    padding = dip(8)
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
                normalSecondaryTextView {
                    id = View.generateViewId().also { tvDescId = it }
                    tvDesc = this
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 3
                    bottomPadding = dip(16)
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT) {
                    topMargin = dip(16)
                    horizontalMargin = dip(16)
                }
                divider {
                    id = View.generateViewId().also { dividerId = it }
                }.lparams(width = MATCH_PARENT, height = dip(1))
                normalSecondaryTextView {
                    id = View.generateViewId().also { tvReadNextId = it }
                    allCaps = true
                    text = resources.getString(R.string.discount_card_action)
                    bottomPadding = dip(16)
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT) {
                    topMargin = dip(16)
                    horizontalMargin = dip(16)
                }
                applyConstraintSet {
                    ivImageId {
                        connect(TOP to TOP of PARENT_ID)
                    }
                    tvTitleId {
                        connect(BOTTOM to BOTTOM of ivImageId)
                    }
                    tvDescId {
                        connect(TOP to BOTTOM of tvTitleId)
                    }
                    dividerId {
                        connect(TOP to BOTTOM of tvDescId)
                    }
                    tvReadNextId {
                        connect(TOP to BOTTOM of dividerId)
                    }
                }
            }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
        }
    }
}
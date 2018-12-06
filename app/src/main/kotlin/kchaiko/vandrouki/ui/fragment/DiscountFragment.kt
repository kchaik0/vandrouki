package kchaiko.vandrouki.ui.fragment

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintSet.PARENT_ID
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DetailedDiscount
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.extensions.MATCH_PARENT
import kchaiko.vandrouki.extensions.WRAP_CONTENT
import kchaiko.vandrouki.extensions.createView
import kchaiko.vandrouki.extensions.getThemeAttrColor
import kchaiko.vandrouki.ui.styles.extraLargeDarkTextView
import kchaiko.vandrouki.ui.styles.smallSecondaryTextView
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kotlinx.android.synthetic.main.fragment_discount.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.koin.android.viewmodel.ext.android.viewModel

class DiscountFragment : BaseFragment() {

    companion object {
        private const val DISCOUNT_ARG = "discount"
        fun newInstance(discount: Discount) = DiscountFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DISCOUNT_ARG, discount)
            }
        }
    }

    private val viewModel: DiscountViewModel by viewModel()
    private lateinit var discount: Discount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discount = arguments?.getParcelable(DISCOUNT_ARG)!!
        with(viewModel) {
            loadingDelegate { showLoadingIndicator(it) }
            dataDelegate { populateData(it) }
            errorDelegate { proceedError(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            DiscountUI(discount).createView(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        organizeLayout()
        //viewModel.provideData(discount.detailUrlPart)
    }

    private fun organizeLayout() {
        with(discount) {
            //Picasso.with(activity).load(image).into(ivImage)
//            fd_date.text = getDateFormatted()
//            fd_favourite.setOnClickListener {
//                ClickListener(viewModel).onClick(it)
//            }
//            fd_favourite.isChecked = viewModel.getFavourite(detailUrlPart)
//            fd_author.text = author
//            fd_title.text = title
        }
//        fd_full_desc.movementMethod = LinkMovementMethod()
    }

    private fun showLoadingIndicator(showLoading: Boolean) {
        fd_full_desc.visibility = if (showLoading) View.GONE else View.VISIBLE
        fd_loading.visibility = if (showLoading) View.VISIBLE else View.GONE
    }

    private fun populateData(detailedDiscount: DetailedDiscount) {
        with(detailedDiscount.fullDesc) {
            @Suppress("DEPRECATION")
            fd_full_desc.text = if (this.isNotEmpty())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
                else
                    Html.fromHtml(this)
            else ""
        }
    }

    inner class ClickListener(private val viewModel: DiscountViewModel) {
        fun onClick(view: View) {
            viewModel.saveData(discount, (view as CheckBox).isChecked)
        }
    }

    private class DiscountUI(private val discount: Discount) : AnkoComponent<DiscountFragment> {
        override fun createView(ui: AnkoContext<DiscountFragment>): View = with(ui) {
            scrollView {
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                backgroundColor = ui.ctx.getThemeAttrColor(R.attr.colorDefault)
                isFillViewport = true
                constraintLayout {
                    var ivImageId = 0
                    var tvDateId = 0
                    var cbFavouriteId = 0
                    var tvAuthorId = 0
                    var tvTitleId = 0
                    imageView {
                        id = View.generateViewId().also { ivImageId = it }
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        Picasso.with(ui.ctx).load(discount.image).into(this)
                    }.lparams(width = MATCH_PARENT, height = dip(320))
                    smallSecondaryTextView {
                        id = View.generateViewId().also { tvDateId = it }
                        text = discount.getDateFormatted()
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    checkBox {
                        id = View.generateViewId().also { cbFavouriteId = it }
                        buttonDrawableResource = R.drawable.favourite_selector
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    smallSecondaryTextView {
                        id = View.generateViewId().also { tvAuthorId = it }
                        text = discount.author
                        setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, 0, 0)
                        compoundDrawablePadding = dip(3)
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    extraLargeDarkTextView {
                        id = View.generateViewId().also { tvTitleId = it }
                        text = discount.title
                        //typeface = medium
                    }.lparams(width = WRAP_CONTENT, height = WRAP_CONTENT)
                    applyConstraintSet {
                        ivImageId {
                            connect(
                                    TOP to TOP of PARENT_ID
                            )
                        }
                        tvDateId {
                            connect(
                                    TOP to TOP of cbFavouriteId,
                                    BOTTOM to BOTTOM of cbFavouriteId,
                                    START to START of PARENT_ID margin dip(16)
                            )
                        }
                        cbFavouriteId {
                            connect(
                                    TOP to BOTTOM of ivImageId margin dip(16),
                                    END to END of PARENT_ID margin dip(16)
                            )
                        }
                        tvAuthorId {
                            connect(
                                    TOP to BOTTOM of cbFavouriteId margin dip(8),
                                    START to START of tvDateId margin dip(1)
                            )
                        }
                        tvTitleId {
                            connect(
                                    TOP to BOTTOM of tvAuthorId margin dip(14),
                                    START to START of tvAuthorId,
                                    END to END of cbFavouriteId
                            )
                        }
                    }
                }.lparams(width = MATCH_PARENT, height = WRAP_CONTENT)
            }
        }
    }

}
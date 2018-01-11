package kchaiko.vandrouki.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import kotlin.properties.Delegates

/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private val dataset: List<Discount>, private val itemClick: (Discount) -> Unit)
    : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.itemView.setOnClickListener {
            itemClick(discountBean)
        }
        holder.titleText.text = discountBean.title
        VandroukiApp.INSTANCE.picasso.load(discountBean.image).into(holder.bgImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DiscountItemUI().createView(AnkoContext.create(parent.context, parent)).tag as ViewHolder
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class DiscountItemUI : AnkoComponent<ViewGroup> {

        @ColorInt
        fun Context.getColorFromAttribute(@AttrRes attr: Int): Int {
            val typedValue = TypedValue()
            theme.resolveAttribute(attr, typedValue, true)
            return typedValue.data
        }

        @SuppressLint("SetTextI18n")
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            var titleText: TextView by Delegates.notNull()
            var bgImage: ImageView by Delegates.notNull()
            val itemView = with(ui) {
                cardView {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(8)
                    }
                    cardElevation = px2dip(4)
                    radius = px2dip(4)
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        orientation = LinearLayout.VERTICAL
                        frameLayout {
                            lparams(width = matchParent, height = matchParent)
                            bgImage = imageView(R.drawable.test_bg) {
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }.lparams(width = matchParent, height = matchParent)
                            titleText = themedTextView(theme = R.style.TextViewStyle_Light_Large,
                                    text = "asdasdqweqwe qewbhjqhwej qbwjebqwebhj qbwhjebqhwehj bqhwebhjqjbhwe bhqwjbehqjb bhjqwebhjqwbe hbqhwjebhjq bhjqbwehjbqjwe") {
                                ellipsize = TextUtils.TruncateAt.END
                                maxLines = 1
                                padding = dip(8)
                                backgroundColor = context.getColorFromAttribute(R.attr.colorCardNameBg)
                            }.lparams(width = matchParent, height = wrapContent, gravity = Gravity.BOTTOM)
                        }
                        themedTextView(theme = R.style.TextViewStyle_Secondary_Normal,
                                text = "asdqweqweqweqweqweqwe qwdasd qweqwe asdqdqweqwe qweqweqwe") {
                            ellipsize = TextUtils.TruncateAt.END
                            maxLines = 3
                        }.lparams(width = wrapContent, height = wrapContent) {
                            margin = dip(8)
                        }
                    }
                }
            }
            itemView.tag = ViewHolder(itemView, titleText, bgImage)
            return itemView
        }
    }

    class ViewHolder(itemView: View, val titleText: TextView, val bgImage: ImageView) : RecyclerView.ViewHolder(itemView)

}
package kchaiko.vandrouki.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.ui.item.DiscountItem
import org.jetbrains.anko.AnkoContext

/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private val dataset: List<Discount>, private val itemClick: (Discount) -> Unit)
    : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.bindViews(discountBean, itemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DiscountItem().createView(AnkoContext.create(parent.context, parent)).tag as ViewHolder
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView: View, private val titleText: TextView, private val bgImage: ImageView,
                     val descText: TextView) : RecyclerView.ViewHolder(itemView) {
        fun bindViews(discountBean: Discount, itemClick: (Discount) -> Unit) {
            itemView.setOnClickListener {
                itemClick(discountBean)
            }
            titleText.text = discountBean.title
            VandroukiApp.INSTANCE.picasso.load(discountBean.image)
                    .placeholder(R.drawable.bg_card_holder_image)
                    .error(R.drawable.bg_card_error_image)
                    .into(bgImage)
            descText.text = discountBean.desc
        }
    }

}
package kchaiko.vandrouki.ui.recycler.item

import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kotlinx.android.synthetic.main.item_discount.view.*

class DiscountItem(val discountBean: Discount) : BaseItem {

    override fun getType() = R.layout.item_discount

    override fun bind(holder: RecyclerView.ViewHolder) {
        with(holder.itemView) {
            Picasso.with(context).load(discountBean.image).into(id_image)
            id_title.text = discountBean.title
            id_desc.text = discountBean.desc
        }
    }
}
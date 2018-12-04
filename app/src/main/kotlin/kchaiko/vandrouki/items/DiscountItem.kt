package kchaiko.vandrouki.items

import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kotlinx.android.synthetic.main.item_discount.view.*

class DiscountItem(private val discountBean: Discount, private val itemClick: (Discount) -> Unit) : BaseRecyclerItem {

    override fun getType() = R.layout.item_discount

    override fun bind(holder: RecyclerView.ViewHolder) {
        with(holder.itemView) {
            setOnClickListener {
                itemClick.invoke(discountBean)
            }
            Picasso.with(context).load(discountBean.image).into(id_image)
            id_title.text = discountBean.title
            id_desc.text = discountBean.desc
        }
    }
}
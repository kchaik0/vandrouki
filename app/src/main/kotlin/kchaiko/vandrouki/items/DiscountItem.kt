package kchaiko.vandrouki.items

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ItemDiscountBinding

class DiscountItem(private val discountBean: Discount, private val itemClick: (Discount) -> Unit) : BaseRecyclerItem {

    override fun getType() = R.layout.item_discount

    override fun bind(holder: RecyclerView.ViewHolder) {
        with(holder as DiscountViewHolder) {
            binding?.apply {
                root.setOnClickListener {
                    itemClick(discountBean)
                }
                discount = discountBean
            }
        }
    }

}

class DiscountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = DataBindingUtil.bind<ItemDiscountBinding>(itemView)

}
package kchaiko.vandrouki.items

import android.support.v7.widget.RecyclerView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.BaseViewHolder
import kchaiko.vandrouki.beans.Discount

class DiscountItem(private val discountBean: Discount, private val itemClick: (Discount) -> Unit) : BaseRecyclerItem {

    override fun getType() = R.layout.item_discount

    override fun bind(holder: RecyclerView.ViewHolder) {
        with(holder as BaseViewHolder.DiscountViewHolder) {
            binding?.apply {
                root.setOnClickListener {
                    itemClick(discountBean)
                }
                discount = discountBean
            }
        }
    }
}
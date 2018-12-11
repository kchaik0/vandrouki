package kchaiko.vandrouki.ui.recycler.item

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.ui.recycler.adapter.DiscountRecyclerAdapter

class DiscountItem(val discount: Discount) : BaseItem {
    override val viewType: Int = DiscountRecyclerAdapter.Type.DISCOUNT.ordinal
    override val clickEnable: Boolean = true
}
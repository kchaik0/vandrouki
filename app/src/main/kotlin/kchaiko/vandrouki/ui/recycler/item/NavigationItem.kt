package kchaiko.vandrouki.ui.recycler.item

import kchaiko.vandrouki.ui.recycler.adapter.DiscountRecyclerAdapter

class NavigationItem(var page: Int, private val clickFunction: (Int) -> Unit) : BaseItem {
    override val viewType: Int = DiscountRecyclerAdapter.Type.NAVIGATION.ordinal
    override val clickEnable: Boolean = false

    fun minusPage() {
        clickFunction.invoke(page - 1)
    }

    fun plusPage() {
        clickFunction.invoke(page + 1)
    }
}
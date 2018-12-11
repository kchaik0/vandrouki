package kchaiko.vandrouki.ui.recycler.adapter

import android.view.ViewGroup
import kchaiko.vandrouki.ui.recycler.adapter.DiscountRecyclerAdapter.Type.*
import kchaiko.vandrouki.ui.recycler.holder.*
import kchaiko.vandrouki.ui.recycler.item.BaseItem

class DiscountRecyclerAdapter(itemClick: (BaseItem.() -> Unit)? = null) : BaseRecyclerAdapter(itemClick = itemClick) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder = when (values()[viewType]) {
        DISCOUNT -> DiscountItemViewHolder(parent.context, DiscountItemUI())
        NAVIGATION -> NavigationItemViewHolder(parent.context, NavigationItemUI())
    }

    enum class Type {
        DISCOUNT, NAVIGATION
    }
}
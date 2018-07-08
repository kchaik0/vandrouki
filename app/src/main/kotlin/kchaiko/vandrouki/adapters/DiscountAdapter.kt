package kchaiko.vandrouki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.items.DiscountViewHolder
import kchaiko.vandrouki.items.NavigationViewHolder

/**
 * Created by kchaiko on 08.07.2018.
 */
class DiscountAdapter : BaseRecyclerAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == R.layout.item_discount)
        DiscountViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    else
        NavigationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_navigation, parent, false))
}
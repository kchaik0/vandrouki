package kchaiko.vandrouki.items

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ItemNavigationBinding

class NavigationItem : BaseRecyclerItem {

    override fun getType() = R.layout.item_navigation

    override fun bind(holder: RecyclerView.ViewHolder) {

    }

}

class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = DataBindingUtil.bind<ItemNavigationBinding>(itemView)

}
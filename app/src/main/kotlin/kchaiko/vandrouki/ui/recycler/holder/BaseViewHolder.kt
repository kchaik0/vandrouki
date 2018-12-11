package kchaiko.vandrouki.ui.recycler.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import kchaiko.vandrouki.ui.recycler.item.BaseItem

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(data: BaseItem)
}
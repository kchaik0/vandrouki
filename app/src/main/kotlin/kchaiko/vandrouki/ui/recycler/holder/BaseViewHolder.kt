package kchaiko.vandrouki.ui.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kchaiko.vandrouki.ui.recycler.item.BaseItem

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(data: BaseItem)
}
package kchaiko.vandrouki.ui.recycler.adapter

import androidx.recyclerview.widget.RecyclerView
import kchaiko.vandrouki.ui.recycler.holder.BaseViewHolder
import kchaiko.vandrouki.ui.recycler.item.BaseItem

abstract class BaseRecyclerAdapter(private val dataset: MutableList<BaseItem> = mutableListOf(), private val itemClick: (BaseItem.() -> Unit)? = null)
    : RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount(): Int = dataset.size

    override fun getItemViewType(position: Int): Int = dataset[position].viewType

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        val item = dataset[position]
        if (item.clickEnable) {
            viewHolder.itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }
        viewHolder.bindData(item)
    }

    fun addItems(items: List<BaseItem>) {
        dataset.clear()
        dataset.addAll(items)
        notifyDataSetChanged()
    }

}
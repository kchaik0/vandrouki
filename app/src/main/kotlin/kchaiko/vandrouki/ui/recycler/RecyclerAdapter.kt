package kchaiko.vandrouki.ui.recycler

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kchaiko.vandrouki.ui.recycler.item.BaseItem


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class RecyclerAdapter(private var dataset: MutableList<BaseItem> = mutableListOf(), private val itemClick: (BaseItem.() -> Unit)? = null)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { itemClick?.invoke(dataset[position]) }
        dataset[position].bind(holder)
    }

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int) = dataset[position].getType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateView(parent, viewType)
        return ViewHolder(view)
    }

    fun addItems(items: List<BaseItem>) {
        dataset.clear()
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutId: Int): View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
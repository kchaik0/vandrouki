package kchaiko.vandrouki.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.items.BaseRecyclerItem


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
abstract class BaseRecyclerAdapter(private var dataset: MutableList<BaseRecyclerItem> = mutableListOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataset[position].bind(holder)
    }

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int) = dataset[position].getType()

    fun addItems(items: List<BaseRecyclerItem>) {
        dataset.clear()
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    protected fun inflateView(parent: ViewGroup, @LayoutRes layoutId: Int) = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

}
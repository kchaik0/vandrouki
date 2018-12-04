package kchaiko.vandrouki.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kchaiko.vandrouki.items.BaseRecyclerItem


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class RecyclerAdapter(private var dataset: MutableList<BaseRecyclerItem> = mutableListOf())
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataset[position].bind(holder)
    }

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int) = dataset[position].getType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateView(parent, viewType)
        return ViewHolder(view)
    }

    fun addItems(items: List<BaseRecyclerItem>) {
        dataset.clear()
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutId: Int): View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
package kchaiko.vandrouki.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ItemDiscountBinding
import kchaiko.vandrouki.databinding.ItemNavigationBinding
import kchaiko.vandrouki.items.BaseRecyclerItem


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private var dataset: MutableList<BaseRecyclerItem> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataset[position].bind(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == R.layout.item_discount)
        DiscountViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    else
        NavigationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_navigation, parent, false))

    override fun getItemCount() = dataset.size

    override fun getItemViewType(position: Int) = dataset[position].getType()

    fun addItems(items: List<BaseRecyclerItem>) {
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemNavigationBinding>(itemView)

    }

    class DiscountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemDiscountBinding>(itemView)

    }

}
package kchaiko.vandrouki.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ItemDiscountBinding
import kchaiko.vandrouki.databinding.ItemNavigationBinding
import kchaiko.vandrouki.items.BaseRecyclerItem
import kchaiko.vandrouki.network.exception.VandException


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
        return when (viewType) {
            R.layout.item_discount -> BaseViewHolder.DiscountViewHolder(view)
            R.layout.item_navigation -> BaseViewHolder.NavigationViewHolder(view)
            else -> throw VandException("Need to describe all holders with layout in code above")
        }
    }

    fun addItems(items: List<BaseRecyclerItem>) {
        dataset.clear()
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    private fun inflateView(parent: ViewGroup, @LayoutRes layoutId: Int): View = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

}

sealed class BaseViewHolder<VDB : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract val binding: VDB?

    class DiscountViewHolder(itemView: View) : BaseViewHolder<ItemDiscountBinding>(itemView) {
        override val binding: ItemDiscountBinding?
            get() = DataBindingUtil.bind(itemView)
    }

    class NavigationViewHolder(itemView: View) : BaseViewHolder<ItemNavigationBinding>(itemView) {
        override val binding: ItemNavigationBinding?
            get() = DataBindingUtil.bind(itemView)
    }
}
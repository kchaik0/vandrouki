package kchaiko.vandrouki.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kchaiko.vandrouki.R
import kchaiko.vandrouki.items.DiscountViewHolder
import kchaiko.vandrouki.items.NavigationViewHolder

/**
 * Created by kchaiko on 08.07.2018.
 */
class DiscountAdapter : BaseRecyclerAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateView(parent, viewType)
        return if (viewType == R.layout.item_discount)
            DiscountViewHolder(view)
        else
            NavigationViewHolder(view)
    }
}
package kchaiko.vandrouki.items

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView

interface BaseRecyclerItem {

    @LayoutRes
    fun getType(): Int

    fun bind(holder: RecyclerView.ViewHolder)

}
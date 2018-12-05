package kchaiko.vandrouki.ui.recycler.item

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView

interface BaseItem {

    @LayoutRes
    fun getType(): Int

    fun bind(holder: RecyclerView.ViewHolder)

}
package kchaiko.vandrouki.items

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ItemNavigationBinding

class NavigationItem(private val page: Int, private val clickFunction: (Int) -> Unit) : BaseRecyclerItem {

    override fun getType() = R.layout.item_navigation

    override fun bind(holder: RecyclerView.ViewHolder) {
        (holder as NavigationViewHolder).binding?.apply {
            this.page = this@NavigationItem.page
            handler = ClickHandler(clickFunction)
        }
    }

    inner class ClickHandler(private val clickFunction: (Int) -> Unit) {
        fun onClick(view: View) {
            val newPage = if (view.id == R.id.prevPage) page + 1 else page - 1
            clickFunction(newPage)
        }
    }
}

class NavigationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = DataBindingUtil.bind<ItemNavigationBinding>(itemView)

}
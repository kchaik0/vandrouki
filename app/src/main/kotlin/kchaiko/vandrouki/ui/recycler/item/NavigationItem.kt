package kchaiko.vandrouki.ui.recycler.item

import android.support.v7.widget.RecyclerView
import android.view.View
import kchaiko.vandrouki.R
import kchaiko.vandrouki.network.service.DEFAULT_PAGE
import kotlinx.android.synthetic.main.item_navigation.view.*

class NavigationItem(private val page: Int, private val clickFunction: (Int) -> Unit) : BaseItem {

    override fun getType() = R.layout.item_navigation

    override fun bind(holder: RecyclerView.ViewHolder) {
        val clickHandler = ClickHandler(clickFunction)
        holder.itemView.in_prev_page.setOnClickListener {
            clickHandler.onClick(it)
        }
        holder.itemView.in_next_page.visibility = if (page > DEFAULT_PAGE) View.VISIBLE else View.GONE
        holder.itemView.in_next_page.setOnClickListener {
            clickHandler.onClick(it)
        }
    }

    inner class ClickHandler(private val clickFunction: (Int) -> Unit) {
        fun onClick(view: View) {
            val newPage = if (view.id == R.id.in_prev_page) page + 1 else page - 1
            clickFunction(newPage)
        }
    }
}
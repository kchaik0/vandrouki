package kchaiko.vandrouki.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.DiscountBean
import kchaiko.vandrouki.enumes.DateFormats
import kotlinx.android.synthetic.main.item_discount.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(var dataset: List<DiscountBean>) : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat(DateFormats.SHOW_FORMAT.format, Locale.getDefault())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.tvTitle.text = discountBean.title
        holder.tvDate.text = dateFormat.format(discountBean.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView
        val tvDate: TextView

        init {
            tvTitle = itemView.id_title
            tvDate = itemView.id_date
        }

    }

}
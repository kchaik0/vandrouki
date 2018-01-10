package kchaiko.vandrouki.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import kotlinx.android.synthetic.main.item_discount.view.*

/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private val dataset: List<Discount>, private val itemClick: (Discount) -> Unit)
    : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    //private val dateFormat: SimpleDateFormat = SimpleDateFormat(DateFormats.SHOW_FORMAT.format, Locale.getDefault())

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.itemView.setOnClickListener {
            itemClick(discountBean)
        }
        holder.tvTitle.text = discountBean.title
        VandroukiApp.INSTANCE.picasso.load(discountBean.image).into(holder.ivType)
        //holder.tvDate.text = dateFormat.format(discountBean.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.id_title
        val ivType: ImageView = itemView.id_type
        //val tvDate: TextView = itemView.id_date

    }

}
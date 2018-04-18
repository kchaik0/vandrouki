package kchaiko.vandrouki.adapters

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ItemDiscountBinding


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private var dataset: MutableList<Discount> = mutableListOf(), private val itemClick: (Discount) -> Unit) : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(imageView: ImageView, v: String) {
            Picasso.with(imageView.context).load(v).into(imageView)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        with(holder) {
            binding?.apply {
                root.setOnClickListener {
                    itemClick(discountBean)
                }
                discount = discountBean
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))

    override fun getItemCount() = dataset.size

    fun addItems(items: List<Discount>) {
        dataset.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemDiscountBinding>(itemView)

    }

}
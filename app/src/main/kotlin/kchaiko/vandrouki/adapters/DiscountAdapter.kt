package kchaiko.vandrouki.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ItemDiscountBinding
import kotlinx.android.synthetic.main.item_discount.view.*
import javax.inject.Inject
import android.databinding.BindingAdapter
import android.widget.ImageView


/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private val dataset: List<Discount>, private val itemClick: (Discount) -> Unit)
    : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(imageView: ImageView, v: String) {
            Picasso.with(imageView.context).load(v).into(imageView)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.bindViews(discountBean, itemClick)
        holder.binding?.discount = discountBean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemDiscountBinding>(itemView)

        fun bindViews(discountBean: Discount, itemClick: (Discount) -> Unit) {
            itemView.setOnClickListener {
                itemClick(discountBean)
            }
        }
    }

}
package kchaiko.vandrouki.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.beans.Discount
import kotlinx.android.synthetic.main.item_discount.view.*
import javax.inject.Inject

/**
 * Adapter for showing discount items
 *
 * Created by kchaiko on 05.07.2017.
 */
class DiscountAdapter(private val dataset: List<Discount>, private val itemClick: (Discount) -> Unit)
    : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discountBean = dataset[position]
        holder.bindViews(discountBean, itemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_discount, parent, false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleText = itemView.id_title
        private val bgImage = itemView.id_type
        private val descText = itemView.id_desc

        @Inject
        lateinit var picasso: Picasso

        init {
            VandroukiApp.appComponent.inject(this)
        }

        fun bindViews(discountBean: Discount, itemClick: (Discount) -> Unit) {
            itemView.setOnClickListener {
                itemClick(discountBean)
            }
            titleText.text = discountBean.title
            picasso.load(discountBean.image)
                    .placeholder(R.drawable.bg_card_holder_image)
                    .error(R.drawable.bg_card_error_image)
                    .into(bgImage)
            descText.text = discountBean.desc
        }
    }

}
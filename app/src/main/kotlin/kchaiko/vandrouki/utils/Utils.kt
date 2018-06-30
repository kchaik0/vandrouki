package kchaiko.vandrouki.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, v: String) {
    Picasso.with(imageView.context).load(v).into(imageView)
}
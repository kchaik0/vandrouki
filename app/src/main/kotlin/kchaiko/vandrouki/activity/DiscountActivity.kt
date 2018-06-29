package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.koin.android.ext.android.inject

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, DiscountActivity::class.java)

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(imageView: ImageView, v: String) {
            Picasso.with(imageView.context).load(v).into(BitmapLoadTarget(imageView))
        }
    }

    val viewModel: DiscountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = flags
        }*/
        DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
                .apply {
                    viewModel = this@DiscountActivity.viewModel
                }
    }

    class BitmapLoadTarget(val imageView: ImageView) : Target {

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
            imageView.setImageBitmap(bitmap)
        }

    }
}

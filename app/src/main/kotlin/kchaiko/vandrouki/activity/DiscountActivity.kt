package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.di.KEY_DISCOUNT
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.property

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, DiscountActivity::class.java)
    }

    private val viewModel: DiscountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
                .apply {
                    viewModel = this@DiscountActivity.viewModel
                }
    }
}

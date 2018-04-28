package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.annotation.ViewModel
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import javax.inject.Inject

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    @Inject
    @ViewModel
    lateinit var discountViewModel: DiscountViewModel

    companion object {

        private const val DISCOUNT_EXTRA = "discount"

        fun getIntent(context: Context, discount: Discount) = Intent(context, DiscountActivity::class.java)
                .apply { putExtra(DISCOUNT_EXTRA, discount) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        discountViewModel.discount = intent.getParcelableExtra(DISCOUNT_EXTRA)
        DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
                .apply {
                    viewModel = discountViewModel
                }
    }
}

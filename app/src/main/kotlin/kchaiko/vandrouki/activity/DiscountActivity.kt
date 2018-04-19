package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import javax.inject.Inject

/**
 * Activity for show discount details
 *
 * Created by kchaiko on 06.07.2017.
 */
class DiscountActivity : BaseActivity() {

    @Inject
    lateinit var discountViewModel: DiscountViewModel

    companion object {

        fun getIntent(context: Context) = Intent(context, DiscountActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
                .apply {
                    viewModel = discountViewModel
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        VandroukiApp.INSTANCE.clearDiscountScope()
    }

    override fun getViewModel() = discountViewModel
}

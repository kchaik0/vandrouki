package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import org.koin.android.ext.android.inject
import java.util.*

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
    private lateinit var binding: ActivityDiscountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        with(viewModel) {
            loadingDelegate { binding.isLoading = it }
            dataDelegate { binding.detailedDiscount = it }
            errorDelegate { proceedError(it) }
            provideData()
        }
    }

    private fun initBinding() = DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
            .apply {
                fullDescTV.movementMethod = LinkMovementMethod()
                isLoading = false
                discount = viewModel.discount
                isFavourite = isFavourite()
            }

    private fun isFavourite() = Random().nextBoolean()
}

package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import org.koin.android.ext.android.inject

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

    inner class ClickListener {
        fun onClick(view: View) {
            viewModel.saveData((view as CheckBox).isChecked)
        }
    }

    private fun initBinding() = DataBindingUtil.setContentView<ActivityDiscountBinding>(this, R.layout.activity_discount)
            .apply {
                clickListener = ClickListener()
                fullDescTV.movementMethod = LinkMovementMethod()
                isLoading = false
                discount = viewModel.discount
                isFavourite = viewModel.getFavourite()
            }
}

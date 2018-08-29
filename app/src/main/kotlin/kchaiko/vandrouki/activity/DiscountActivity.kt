package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
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
    private val binding: ActivityDiscountBinding by inject { mapOf("activity" to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            loadingDelegate { binding.isLoading = it }
            dataDelegate { binding.detailedDiscount = it }
            errorDelegate { proceedError(it) }
            provideData()
        }
    }
}

class ClickListener(private val viewModel: DiscountViewModel) {
    fun onClick(view: View) {
        viewModel.saveData((view as CheckBox).isChecked)
    }
}

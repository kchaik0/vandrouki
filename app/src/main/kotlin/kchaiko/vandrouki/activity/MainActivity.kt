package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.di.setDiscount
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        viewModel.loadingDelegate { binding.isLoading = it }
                .dataDelegate { binding.adapter?.addItems(it) }
                .errorDelegate { proceedError(it) }
                .provideData()
    }

    private fun initBinding() = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                isLoading = false
                adapter = DiscountAdapter {
                    setDiscount(it)
                    startActivity(DiscountActivity.getIntent(this@MainActivity))
                }
            }
}

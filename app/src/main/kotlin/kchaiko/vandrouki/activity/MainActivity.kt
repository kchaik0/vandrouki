package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.di.setDiscount
import kchaiko.vandrouki.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val viewModel: MainViewModel by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    viewModel = this@MainActivity.viewModel
                    adapter = DiscountAdapter {
                        setDiscount(it)
                        startActivity(DiscountActivity.getIntent(this@MainActivity))
                    }
                }
        viewModel.dataDelegate { binding.adapter?.addItems(it) }
                .errorDelegate { proceedError(it) }
                .provideData()
    }
}

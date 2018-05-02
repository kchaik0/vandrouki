package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.viewmodel.MainViewModel

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    viewModel = super.viewModel
                    adapter = DiscountAdapter {
                        startActivity(DiscountActivity.getIntent(this@MainActivity, it))
                    }
                }
        viewModel.dataDelegate { binding.adapter?.addItems(it) }
                .errorDelegate { proceedError(it) }
                .subscribe()
    }
}

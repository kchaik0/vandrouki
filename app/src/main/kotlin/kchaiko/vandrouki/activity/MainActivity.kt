package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel2 = MainViewModel.newInstance()
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    viewModel = viewModel2
                    adapter = DiscountAdapter {
                        startActivity(DiscountActivity.getIntent(this@MainActivity, it))
                    }
                }
        viewModel2.dataDelegate { binding.adapter?.addItems(it) }
                .errorDelegate { proceedError(it) }
                .loadData()
    }
}

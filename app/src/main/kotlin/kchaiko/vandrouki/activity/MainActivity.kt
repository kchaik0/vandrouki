package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.annotation.ViewModel
import kchaiko.vandrouki.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    @ViewModel
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    viewModel = mainViewModel
                    adapter = DiscountAdapter {
                        startActivity(DiscountActivity.getIntent(this@MainActivity, it))
                    }
                }
        mainViewModel.dataDelegate { binding.adapter?.addItems(it) }
                .errorDelegate { proceedError(it) }
                .subscribe()
    }
}

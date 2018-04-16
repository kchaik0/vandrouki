package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var discountAdapter: DiscountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(mainViewModel) {
            dataDelegate { discountAdapter.addItems(it) }
            errorDelegate { proceedError(it) }
        }
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply { viewModel = mainViewModel }
                .apply {
                    discountAdapter = DiscountAdapter { startActivity(DiscountActivity.getIntent(this@MainActivity, it)) }
                    adapter = discountAdapter
                }
        mainViewModel.subscribe()
    }

    override fun getViewModel() = mainViewModel
}

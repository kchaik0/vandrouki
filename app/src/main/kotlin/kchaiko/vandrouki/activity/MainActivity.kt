package kchaiko.vandrouki.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainViewModel.itemClick = {
            startActivity(DiscountActivity.getIntent(this, it))
        }
        binding.viewModel = mainViewModel
        mainViewModel.subscribe()
    }


}
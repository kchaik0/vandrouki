package kchaiko.vandrouki.activity

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kchaiko.vandrouki.R
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel.discountListLiveData.observe(this, Observer { provideResult(it) })
    }

    private fun provideResult(result: Resource<List<Discount>>?) {
        when (result?.status) {
            RequestStatus.SUCCESS -> {
                activityMainBinding.adapter = result.data?.let { data -> DiscountAdapter(data, { startActivity(DiscountActivity.getIntent(this, it)) }) }
                activityMainBinding.resource = result
            }
            RequestStatus.ERROR -> {
                activityMainBinding.resource = result
                result.exception?.run {
                    proceedError(this)
                }
            }
            RequestStatus.LOADING -> {
                activityMainBinding.resource = result
            }
        }
    }
}
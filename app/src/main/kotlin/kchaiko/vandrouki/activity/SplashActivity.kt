package kchaiko.vandrouki.activity

import android.os.Bundle
import kchaiko.vandrouki.viewmodel.SplashViewModel
import javax.inject.Inject

/**
 * First splash activity in app
 *
 * Created by Chayko_KA on 25.01.2018.
 */
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.loadDiscounts()
        startActivity(MainActivity.getIntent(this))
        finish()
    }

    override fun getViewModel() = splashViewModel
}
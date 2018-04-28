package kchaiko.vandrouki.activity

import android.os.Bundle
import kchaiko.vandrouki.annotation.ViewModel
import kchaiko.vandrouki.viewmodel.SplashViewModel
import javax.inject.Inject

/**
 * First splash activity in app
 *
 * Created by Chayko_KA on 25.01.2018.
 */
class SplashActivity : BaseActivity() {

    @Inject
    @ViewModel
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.loadDiscounts()
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
package kchaiko.vandrouki.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import kchaiko.vandrouki.viewmodel.SplashViewModel

/**
 * First splash activity in app
 *
 * Created by Chayko_KA on 25.01.2018.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModelProviders.of(this).get(SplashViewModel::class.java).loadDiscounts()
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
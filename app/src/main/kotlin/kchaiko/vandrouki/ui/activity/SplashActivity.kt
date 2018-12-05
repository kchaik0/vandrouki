package kchaiko.vandrouki.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * First splash activity in app
 *
 * Created by Chayko_KA on 25.01.2018.
 */
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
        startActivity(HomeActivity.getIntent(this@SplashActivity))
        finish()
    }
}
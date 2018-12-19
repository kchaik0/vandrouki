package kchaiko.vandrouki.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * First splash activity in app
 *
 * Created by Chayko_KA on 25.01.2018.
 */
class SplashActivity : FragmentActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
        startActivity(HomeActivity.getIntent(this@SplashActivity))
        finish()
    }
}
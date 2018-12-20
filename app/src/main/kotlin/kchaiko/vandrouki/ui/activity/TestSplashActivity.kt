package kchaiko.vandrouki.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestSplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}
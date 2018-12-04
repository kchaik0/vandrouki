package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.db.initObjectBox
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.repository.initRetrofit
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

    private val appModule = module {
        viewModel { SplashViewModel(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { DiscountViewModel(get(), get()) }
        single { DiscountRepository(get()) }
        single { initObjectBox() }
        single { initRetrofit() }
    }

}
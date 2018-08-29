package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.db.initObjectBox
import kchaiko.vandrouki.di.activityContext
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.repository.initRetrofit
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(appModule))
    }

    private val appModule = applicationContext {
        activityContext()
        bean { DiscountRepository(get()) }
        bean { initObjectBox() }
        bean { initRetrofit() }
    }

}
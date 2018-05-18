package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.di.discountActivityContext
import kchaiko.vandrouki.di.mainActivityContext
import kchaiko.vandrouki.di.splashActivityContext
import kchaiko.vandrouki.repository.DiscountRepository
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
        splashActivityContext()
        mainActivityContext()
        discountActivityContext()
        bean { DiscountRepository() }
    }

}
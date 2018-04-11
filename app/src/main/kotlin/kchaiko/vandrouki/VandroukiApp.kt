package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.di.AppComponent
import kchaiko.vandrouki.di.DaggerAppComponent
import kchaiko.vandrouki.di.modules.PicassoModule

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().picassoModule(PicassoModule(this)).build()
    }
}
package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.bus.RxBus

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        val bus: RxBus = RxBus()
    }

}
package kchaiko.vandrouki

import android.app.Application

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
        var bus: RxBus? = null

        init {
            bus = RxBus()
        }

        fun getBusSingleton(): RxBus {
            return bus as RxBus
        }
    }

}
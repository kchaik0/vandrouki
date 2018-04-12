package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.di.DaggerPicassoComponent
import kchaiko.vandrouki.di.PicassoComponent
import kchaiko.vandrouki.di.modules.PicassoModule

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    companion object {
        lateinit var picassoComponent: PicassoComponent
    }

    override fun onCreate() {
        super.onCreate()
        picassoComponent = DaggerPicassoComponent.builder().picassoModule(PicassoModule(this)).build()
    }
}
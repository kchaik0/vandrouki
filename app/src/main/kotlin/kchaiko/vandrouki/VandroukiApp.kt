package kchaiko.vandrouki

import android.app.Application
import com.facebook.stetho.Stetho
import kchaiko.vandrouki.di.getFullGraph
import org.koin.android.ext.android.startKoin

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin(this, getFullGraph())
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

}
package kchaiko.vandrouki

import android.app.Application
import com.squareup.picasso.Picasso

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    companion object {
        lateinit var INSTANCE: VandroukiApp
            private set
    }

    lateinit var picasso: Picasso

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        picasso = Picasso.with(this)
    }
}
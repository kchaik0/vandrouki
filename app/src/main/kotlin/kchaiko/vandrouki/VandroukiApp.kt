package kchaiko.vandrouki

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kchaiko.vandrouki.di.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}
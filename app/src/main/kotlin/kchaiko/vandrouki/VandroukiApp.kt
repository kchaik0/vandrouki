package kchaiko.vandrouki

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kchaiko.vandrouki.di.component.DaggerAppComponent
import kchaiko.vandrouki.di.component.DaggerRepositoryComponent
import kchaiko.vandrouki.di.component.RepositoryComponent
import javax.inject.Inject

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var repositoryComponent: RepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().build().inject(this)
        initDaggerComponents()
    }

    override fun activityInjector() = dispatchingAndroidInjector

    private fun initDaggerComponents() {
        repositoryComponent = DaggerRepositoryComponent.create()
    }
}
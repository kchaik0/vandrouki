package kchaiko.vandrouki

import android.app.Application
import kchaiko.vandrouki.di.component.*

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    companion object {
        lateinit var repositoryComponent: RepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponents()
    }

    private fun initDaggerComponents() {
        repositoryComponent = DaggerRepositoryComponent.create()
    }
}
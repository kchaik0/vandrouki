package kchaiko.vandrouki

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kchaiko.vandrouki.di.component.*
import kchaiko.vandrouki.di.modules.HtmlParserModule
import kchaiko.vandrouki.di.modules.PicassoModule
import kchaiko.vandrouki.di.modules.RepositoryModule
import javax.inject.Inject

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application() {

    companion object {
        lateinit var picassoComponent: PicassoComponent
        lateinit var repositoryComponent: RepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponents()
    }

    private fun initDaggerComponents() {
        picassoComponent = DaggerPicassoComponent.builder().picassoModule(PicassoModule(this)).build()
        repositoryComponent = DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryModule())
                .htmlParserModule(HtmlParserModule())
                .build()
    }
}
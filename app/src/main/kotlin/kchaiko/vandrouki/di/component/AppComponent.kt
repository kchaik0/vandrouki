package kchaiko.vandrouki.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kchaiko.vandrouki.VandroukiApp
import kchaiko.vandrouki.di.module.ActivityBuilder
import kchaiko.vandrouki.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: VandroukiApp)
}
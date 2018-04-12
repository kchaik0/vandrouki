package kchaiko.vandrouki.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kchaiko.vandrouki.VandroukiApp

@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<VandroukiApp>
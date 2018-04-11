package kchaiko.vandrouki.di

import dagger.Component
import kchaiko.vandrouki.activity.MainActivity
import kchaiko.vandrouki.di.modules.PicassoModule
import javax.inject.Singleton

@Component(modules = [PicassoModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}
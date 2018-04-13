package kchaiko.vandrouki.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kchaiko.vandrouki.activity.MainActivity
import kchaiko.vandrouki.activity.SplashActivity
import kchaiko.vandrouki.di.module.activity.MainActivityModule
import kchaiko.vandrouki.di.module.activity.SplashActivityModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

}
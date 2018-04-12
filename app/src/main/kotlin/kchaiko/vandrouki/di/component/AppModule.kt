package kchaiko.vandrouki.di.component

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kchaiko.vandrouki.activity.MainActivity

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun contributeActivityInjector() : MainActivity

}
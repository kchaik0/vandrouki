package kchaiko.vandrouki.di.module.activity

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.viewmodel.DiscountViewModel

@Module
class DiscountActivityModule {

    @Provides
    fun provideViewModel() = DiscountViewModel()

}
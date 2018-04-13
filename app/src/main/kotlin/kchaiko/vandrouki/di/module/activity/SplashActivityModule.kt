package kchaiko.vandrouki.di.module.activity

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.viewmodel.SplashViewModel

@Module
class SplashActivityModule {

    @Provides
    fun provideViewModel(discountRepository: DiscountRepository) = SplashViewModel(discountRepository)

}
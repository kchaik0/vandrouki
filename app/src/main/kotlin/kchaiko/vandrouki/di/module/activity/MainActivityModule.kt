package kchaiko.vandrouki.di.module.activity

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.repository.DiscountRepository
import kchaiko.vandrouki.viewmodel.MainViewModel

@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(discountRepository: DiscountRepository) = MainViewModel(discountRepository)

}
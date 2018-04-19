package kchaiko.vandrouki.di.module

import dagger.Module
import dagger.Provides
import kchaiko.vandrouki.beans.Discount

@Module
class DiscountModule(val discount: Discount) {

    @Provides
    fun provideDiscount(): Discount = discount

}
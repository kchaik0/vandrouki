package kchaiko.vandrouki.di.component

import dagger.Component
import kchaiko.vandrouki.di.module.DiscountModule
import kchaiko.vandrouki.viewmodel.DiscountViewModel

@Component(modules = [DiscountModule::class])
interface DiscountComponent {

    fun inject(discountViewModel: DiscountViewModel)

}
package kchaiko.vandrouki.di

import dagger.Component
import kchaiko.vandrouki.activity.BaseActivity
import kchaiko.vandrouki.activity.MainActivity
import kchaiko.vandrouki.adapters.DiscountAdapter
import kchaiko.vandrouki.di.modules.PicassoModule
import javax.inject.Singleton

@Component(modules = [PicassoModule::class])
@Singleton
interface AppComponent {

    fun inject(viewHolder: DiscountAdapter.ViewHolder)

}
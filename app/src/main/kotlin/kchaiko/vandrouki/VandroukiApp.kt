package kchaiko.vandrouki

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.di.component.AppComponent
import kchaiko.vandrouki.di.component.DaggerAppComponent
import kchaiko.vandrouki.di.component.DaggerDiscountComponent
import kchaiko.vandrouki.di.component.DiscountComponent
import kchaiko.vandrouki.di.module.DiscountModule
import javax.inject.Inject

/**
 * Application
 *
 * Created by kchaiko on 06.07.2017.
 */
class VandroukiApp : Application(), HasActivityInjector {

    companion object {
        lateinit var INSTANCE: VandroukiApp
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    var discountComponent: DiscountComponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    fun buildDiscountScope(discount: Discount) {
        discountComponent = DaggerDiscountComponent.builder().discountModule(DiscountModule(discount)).build()
    }

    fun clearDiscountScope() {
        discountComponent = null
    }
}
package kchaiko.vandrouki.di

import kchaiko.vandrouki.activity.BaseActivity
import kchaiko.vandrouki.activity.DiscountActivity
import kchaiko.vandrouki.activity.MainActivity
import kchaiko.vandrouki.activity.SplashActivity
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import org.koin.android.ext.android.releaseContext
import org.koin.dsl.context.Context

private const val CONTEXT_SPLASH = "splashContext"
private const val CONTEXT_MAIN = "mainContext"
private const val CONTEXT_DISCOUNT = "discountContext"

fun Context.activityContext() {
    splashActivityContext()
    mainActivityContext()
    discountActivityContext()
}

fun BaseActivity.releaseContext() {
    val name = when (this) {
        is SplashActivity -> CONTEXT_SPLASH
        is MainActivity -> CONTEXT_MAIN
        is DiscountActivity -> CONTEXT_DISCOUNT
        else -> throw VandException("Add every activity context to release method")
    }
    releaseContext(name)
}

private fun Context.splashActivityContext() {
    context(CONTEXT_SPLASH) {
        factory { SplashViewModel(get()) }
    }
}

//region Main Activity
private fun Context.mainActivityContext() {
    context(CONTEXT_MAIN) {
        factory { MainViewModel(get()) }
    }
}
//endregion

//region Discount Activity
private fun Context.discountActivityContext() {
    context(CONTEXT_DISCOUNT) {
        factory { DiscountViewModel(get(), get()) }
    }
}
//endregion
package kchaiko.vandrouki.di

import kchaiko.vandrouki.activity.BaseActivity
import kchaiko.vandrouki.activity.DiscountActivity
import kchaiko.vandrouki.activity.MainActivity
import kchaiko.vandrouki.activity.SplashActivity
import kchaiko.vandrouki.viewmodel.DiscountViewModel
import kchaiko.vandrouki.viewmodel.MainViewModel
import kchaiko.vandrouki.viewmodel.SplashViewModel
import org.koin.android.ext.android.releaseContext
import org.koin.dsl.context.Context

private const val CONTEXT_SPLASH = "splashContext"
private const val CONTEXT_MAIN = "mainContext"
private const val CONTEXT_DISCOUNT = "discountContext"

fun Context.splashActivityContext() {
    context(CONTEXT_SPLASH) {
        bean { SplashViewModel(get()) }
    }
}

fun Context.mainActivityContext() {
    context(CONTEXT_MAIN) {
        factory { MainViewModel(get()) }
    }
}

fun Context.discountActivityContext() {
    context(CONTEXT_DISCOUNT) {
        factory { DiscountViewModel(getProperty(KEY_DISCOUNT), get()) }
    }
}

fun BaseActivity.releaseContext() {
    val name = when (this) {
        is SplashActivity -> CONTEXT_SPLASH
        is MainActivity -> CONTEXT_MAIN
        is DiscountActivity -> CONTEXT_DISCOUNT
        else -> ""
    }
    releaseContext(name)
}
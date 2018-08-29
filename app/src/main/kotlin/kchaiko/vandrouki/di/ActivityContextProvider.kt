package kchaiko.vandrouki.di

import android.databinding.DataBindingUtil
import android.text.method.LinkMovementMethod
import kchaiko.vandrouki.R
import kchaiko.vandrouki.activity.*
import kchaiko.vandrouki.adapters.RecyclerAdapter
import kchaiko.vandrouki.databinding.ActivityDiscountBinding
import kchaiko.vandrouki.databinding.ActivityMainBinding
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.viewmodel.load.SplashViewModel
import kchaiko.vandrouki.viewmodel.provide.DiscountViewModel
import kchaiko.vandrouki.viewmodel.provide.MainViewModel
import org.koin.android.ext.android.releaseContext
import org.koin.dsl.context.Context
import org.koin.dsl.context.ParameterProvider

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
        factory { params -> initMainBinding(params) }
    }
}

private fun initMainBinding(params: ParameterProvider) =
        DataBindingUtil.setContentView<ActivityMainBinding>(params["activity"], R.layout.activity_main)
                .apply {
                    isLoading = false
                    adapter = RecyclerAdapter()
                }
//endregion

//region Discount Activity
private fun Context.discountActivityContext() {
    context(CONTEXT_DISCOUNT) {
        factory { DiscountViewModel(get(), get()) }
        factory { params -> initDiscountBinding(params) }
    }
}

private fun Context.initDiscountBinding(params: ParameterProvider) =
        DataBindingUtil.setContentView<ActivityDiscountBinding>(params["activity"], R.layout.activity_discount)
                .apply {
                    clickListener = ClickListener(get())
                    fullDescTV.movementMethod = LinkMovementMethod()
                    isLoading = false
                    discount = get<DiscountViewModel>().discount
                    isFavourite = get<DiscountViewModel>().getFavourite()
                }
//endregion
package kchaiko.vandrouki.viewmodel

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected lateinit var compositeDisposable: CompositeDisposable

    fun attach() {
        compositeDisposable = CompositeDisposable()
    }

    fun detach() {
        compositeDisposable.clear()
    }

}
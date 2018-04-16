package kchaiko.vandrouki.viewmodel

import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel {

    protected val compositeDisposable = CompositeDisposable()

    fun clearViewModel() {
        compositeDisposable.clear()
    }

}
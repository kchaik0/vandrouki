package kchaiko.vandrouki.viewmodel.provide

import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.VandException
import kchaiko.vandrouki.viewmodel.BaseViewModel

abstract class DataViewModel<T> : BaseViewModel(), ProvideDataViewModel {

    private lateinit var dataDelegate: (T) -> Unit
    private lateinit var loadingDelegate: (Boolean) -> Unit
    private lateinit var errorDelegate: (VandException) -> Unit

    fun dataDelegate(function: (T) -> Unit) = apply { dataDelegate = function }
    fun loadingDelegate(function: (Boolean) -> Unit) = apply { loadingDelegate = function }
    fun errorDelegate(function: (VandException) -> Unit) = apply { errorDelegate = function }

    protected fun provideLoading(loading: Boolean = false) {
        loadingDelegate(loading)
    }

    protected fun provideResult(it: Resource<T>) {
        when (it.status) {
            RequestStatus.SUCCESS -> {
                it.data?.apply { dataDelegate(this) }
                provideLoading()
            }
            RequestStatus.ERROR -> {
                provideLoading()
                it.exception?.apply { errorDelegate(this) }
            }
            RequestStatus.LOADING -> provideLoading(true)
        }
    }

}
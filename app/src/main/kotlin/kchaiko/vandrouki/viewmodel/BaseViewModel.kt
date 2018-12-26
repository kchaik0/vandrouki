package kchaiko.vandrouki.viewmodel

import androidx.lifecycle.ViewModel
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.VandException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel<T> : ViewModel() {

    private val viewModelJob = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

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
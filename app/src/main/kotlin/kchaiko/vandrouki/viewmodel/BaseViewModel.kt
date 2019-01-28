package kchaiko.vandrouki.viewmodel

import androidx.lifecycle.MutableLiveData
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

    val modelLiveData = MutableLiveData<T>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<VandException>()

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    protected fun provideLoading(loading: Boolean) {
        loadingLiveData.value = loading
    }

    protected fun provideResult(resource: Resource<T>) {
        when (resource.status) {
            RequestStatus.SUCCESS -> {
                modelLiveData.value = resource.data
            }
            RequestStatus.ERROR -> {
                errorLiveData.value = resource.exception
            }
        }
        provideLoading(false)
    }

}
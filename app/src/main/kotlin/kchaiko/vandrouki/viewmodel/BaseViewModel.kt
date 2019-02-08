package kchaiko.vandrouki.viewmodel

import androidx.lifecycle.LiveData
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

    private val _modelLiveData = MutableLiveData<T>()
    val modelLiveData: LiveData<T> = _modelLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorLiveData = MutableLiveData<VandException>()
    val errorLiveData: LiveData<VandException> = _errorLiveData

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    protected fun provideLoading(loading: Boolean) {
        _loadingLiveData.value = loading
    }

    protected fun provideResult(resource: Resource<T>) {
        when (resource.status) {
            RequestStatus.SUCCESS -> {
                _modelLiveData.value = resource.data
            }
            RequestStatus.ERROR -> {
                _errorLiveData.value = resource.exception
            }
        }
        provideLoading(false)
    }

}
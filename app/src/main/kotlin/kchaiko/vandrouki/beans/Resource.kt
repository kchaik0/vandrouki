package kchaiko.vandrouki.beans

import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.BaseException

/**
 * Bean for wrap request data
 *
 * Created by kchaiko on 11.10.2017.
 */
data class Resource<T>(var status: RequestStatus, var data: T? = null, var exception: BaseException? = null) {

    companion object {
        fun <T> loading() = Resource<T>(RequestStatus.LOADING)
    }

    fun setStatusSuccess(data: T): Resource<T> {
        this.status = RequestStatus.SUCCESS
        this.data = data
        return this
    }

    fun setStatusError(throwable: Throwable): Resource<T> {
        this.status = RequestStatus.ERROR
        this.exception = getExceptionFromThrowable(throwable)
        return this
    }

    fun isLoading() = status == RequestStatus.LOADING

    private fun getExceptionFromThrowable(throwable: Throwable): BaseException? {
        return when (throwable) {
            is BaseException -> throwable
            else -> {
                throwable.printStackTrace()
                null
            }
        }
    }
}
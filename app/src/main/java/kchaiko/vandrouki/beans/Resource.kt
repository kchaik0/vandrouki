package kchaiko.vandrouki.beans

import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.BaseException

/**
 * Bean for wrap request data
 *
 * Created by kchaiko on 11.10.2017.
 */
data class Resource<T>(val status: RequestStatus, val data: T? = null, val exception: BaseException? = null) {
    constructor(status: RequestStatus, exception: BaseException?) : this(status, null, exception)

    companion object {
        fun <T> success(data: T) = Resource(RequestStatus.SUCCESS, data)
        fun <T> loading() = Resource<T>(RequestStatus.LOADING)
        fun <T> error(throwable: Throwable) = Resource<T>(RequestStatus.ERROR, getExceptionFromThrowable(throwable))

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
}
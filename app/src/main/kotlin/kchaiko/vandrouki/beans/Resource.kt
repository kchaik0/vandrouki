package kchaiko.vandrouki.beans

import kchaiko.vandrouki.enumes.request.RequestStatus
import kchaiko.vandrouki.network.exception.VandException
import java.net.UnknownHostException

/**
 * Bean for wrap request data
 *
 * Created by kchaiko on 11.10.2017.
 */
data class Resource<T>(var status: RequestStatus, var data: T? = null, var exception: VandException? = null) {

    companion object {
        fun <T> success(data: T) = Resource(RequestStatus.SUCCESS, data)
        fun <T> error(throwable: Throwable) = Resource<T>(RequestStatus.ERROR, exception = getExceptionFromThrowable(throwable))

        private fun getExceptionFromThrowable(throwable: Throwable) = when (throwable) {
            is UnknownHostException -> VandException(throwable.message)
            else -> {
                throwable.printStackTrace()
                null
            }
        }
    }
}
package kchaiko.vandrouki.beans

/**
 * Bean for wrap request data
 *
 * Created by kchaiko on 11.10.2017.
 */
data class RequestBean<T>(val data: List<T>?, val error: String? = null) {
    constructor(error: String?) : this(null, error)

    fun isSuccess(): Boolean {
        return data != null
    }
}
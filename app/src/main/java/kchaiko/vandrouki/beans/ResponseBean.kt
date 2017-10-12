package kchaiko.vandrouki.beans

/**
 * Bean for wrap request data
 *
 * Created by kchaiko on 11.10.2017.
 */
data class ResponseBean<T>(val data: T?, val error: String? = null) {
    constructor(error: String?) : this(null, error)
}
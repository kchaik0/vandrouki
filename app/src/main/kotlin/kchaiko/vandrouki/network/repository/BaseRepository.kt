package kchaiko.vandrouki.network.repository

import android.net.ConnectivityManager
import android.net.NetworkInfo
import kchaiko.vandrouki.beans.Resource
import kchaiko.vandrouki.network.exception.NetworkException
import kotlinx.coroutines.Deferred
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseRepository : KoinComponent {

    private val connectivityManager: ConnectivityManager by inject()

    protected suspend fun <RES> awaitWithException(deferred: Deferred<RES>): Resource<RES> = try {
        checkNetworkState()
        Resource.success(deferred.await())
    } catch (e: Exception) {
        Resource.error(e)
    }

    private fun checkNetworkState() {
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected) {
            throw NetworkException()
        }
    }

}
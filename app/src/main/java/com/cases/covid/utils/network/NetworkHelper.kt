package com.cases.covid.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Pallab Banerjee on 7/25/2020.
 */

/**
 * Used for checking the status internet connection on user's device.
 * */

@Singleton
class NetworkHelper @Inject constructor (private val context: Context)  {

    fun checkIsNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = cm.activeNetwork ?: return false
            val networkCapabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }

        } else {
            cm.activeNetworkInfo?.isConnected ?: return false

        }



    }

}
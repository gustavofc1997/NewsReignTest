package com.gforeroc.reign.data.utils

import android.content.Context
import android.net.ConnectivityManager
import com.gforeroc.reign.data.helpers.INetworkHelper

class NetworkHelper(private val mContext: Context) : INetworkHelper {

    override fun isInternetAvailable(): Boolean {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}

package com.gforeroc.reign.data.network.interceptors

import com.gforeroc.reign.domain.helpers.INetworkHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class OfflineConnectionInterceptor(
    private val mNetworkHelper: INetworkHelper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (!mNetworkHelper.isInternetAvailable()) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        return chain.proceed(request)

    }
}

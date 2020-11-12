package com.gforeroc.reign.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class OnlineConnectionInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
        return response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()

    }
}

package com.gforeroc.reign.data.network

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkClient {
    suspend fun <T : Any> apiCall(call: Deferred<Response<T>>): Response<T>

    fun getErrorFromResponse(responseAsString: String?): String?
}
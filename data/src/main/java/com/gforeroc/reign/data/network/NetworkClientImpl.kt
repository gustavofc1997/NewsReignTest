package com.gforeroc.reign.data.network

import com.gforeroc.reign.data.utils.NO_INTERNET_CONNECTION
import com.gforeroc.reign.domain.exceptions.BackendErrorContent
import com.gforeroc.reign.domain.exceptions.NetworkException
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection

class NetworkClientImpl : NetworkClient {

    override suspend fun <T : Any> apiCall(call: Deferred<Response<T>>): Response<T> {
        val response = call.await()

        if (response.isSuccessful) {
            return response
        }


        val errorBody = response.errorBody()?.string()

        val error = getErrorFromResponse(
            errorBody
        )

        throw when (response.code()) {
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ConnectException(NO_INTERNET_CONNECTION)
            HttpURLConnection.HTTP_NOT_FOUND -> NetworkException.NotFound(error)
            HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkException.ServerError(error)
            else -> UnknownError(error)
        }
    }

    override fun getErrorFromResponse(responseAsString: String?): String? {

        val errorString = responseAsString ?: return null

        return try {
            Gson().fromJson(errorString, BackendErrorContent::class.java)
                .error
        } catch (e: Exception) {
            null
        }
    }
}
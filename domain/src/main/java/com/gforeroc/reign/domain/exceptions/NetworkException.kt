package com.gforeroc.reign.domain.exceptions

sealed class NetworkException(message: String?) : Throwable(message) {
    class NoConnection(message: String?) :  NetworkException(message)
    class NotFound(message: String?) :  NetworkException(message)
    class ServerError(message: String?) : NetworkException(message)
}

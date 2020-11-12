package com.gforeroc.reignapp.utils

import android.util.Log
import com.gforeroc.reign.data.utils.NO_INTERNET_CONNECTION
import com.gforeroc.reign.domain.exceptions.NetworkException
import com.gforeroc.reignapp.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

interface IErrorProcessor {

    var errorHandler: IErrorHandler?

    fun handleException(error: Throwable) {

        error.printStackTrace()

        when (error) {
            is SocketTimeoutException,
            is ConnectException -> {
                if (NO_INTERNET_CONNECTION == error.message) {
                    errorHandler?.showErrorDialog(R.string.snackbar_no_internet_connection)
                } else {
                    errorHandler?.showErrorDialog(R.string.connectivity_error_snackbar)
                }
            }
            is IllegalStateException -> error.message?.let {
                Log.d(
                    IErrorProcessor::class.java.name,
                    it
                )
            }
            is NetworkException -> handleHttpException(error)
            is HttpException -> errorHandler?.showError(R.string.snackbar_unknown_error)
            is SSLHandshakeException -> errorHandler?.showError(
                R.string.connectivity_error_snackbar
            )
            else -> errorHandler?.showError(R.string.snackbar_unknown_error)
        }
    }

    private fun handleHttpException(error: NetworkException) {
        when (error) {
            is NetworkException.ServerError -> {
                error.message?.let {
                    errorHandler?.showError(it)
                } ?: errorHandler?.showError(R.string.connectivity_error_snackbar)
            }
            else -> errorHandler?.showError(R.string.snackbar_unknown_error)
        }
    }
}

package com.gforeroc.reign.domain.exceptions

data class BackendErrorContent(
    val statusCode: String,
    val error: String,
    val message: String?
)

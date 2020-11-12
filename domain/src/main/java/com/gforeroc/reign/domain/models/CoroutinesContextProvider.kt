package com.gforeroc.reign.domain.models

import kotlin.coroutines.CoroutineContext

data class CoroutinesContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)

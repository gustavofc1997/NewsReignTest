package com.gforeroc.reign.domain.interactors.base

interface BaseInteractor<Response, Params> where Response : Any {

    suspend operator fun invoke(
        params: Params
    ): Response
}

package com.gforeroc.reign.domain.interactors.base

interface BaseInteractor<Response> where Response : Any {

    suspend operator fun invoke(
    ): Response
}

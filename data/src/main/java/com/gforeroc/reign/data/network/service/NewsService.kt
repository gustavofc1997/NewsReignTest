package com.gforeroc.reign.data.network.service

import com.gforeroc.reign.data.models.APINewsResponse
import com.gforeroc.reign.data.utils.GET_NEWS
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET(GET_NEWS)
    fun getNewsList(): Deferred<Response<APINewsResponse>>

}
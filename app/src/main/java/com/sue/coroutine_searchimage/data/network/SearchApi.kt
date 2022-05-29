package com.sue.coroutine_searchimage.data.network

import com.sue.coroutine_searchimage.data.network.dto.SearchImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("v1/search/image")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("start") start: Int? = null,
        @Query("filter") filter: String? = null
    ): SearchImageDto
}
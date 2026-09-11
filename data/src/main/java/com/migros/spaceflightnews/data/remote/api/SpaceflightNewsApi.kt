package com.migros.spaceflightnews.data.remote.api

import com.migros.spaceflightnews.data.remote.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceflightNewsApi {
    @GET("articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ArticlesResponseDto

    @GET("articles")
    suspend fun searchArticles(
        @Query("search") query: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ArticlesResponseDto
}
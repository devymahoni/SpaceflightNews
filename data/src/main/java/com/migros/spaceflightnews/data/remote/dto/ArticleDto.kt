package com.migros.spaceflightnews.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ArticleDto>
)

@Serializable
data class ArticleDto(
    val id: Int,
    val title: String,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("news_site")
    val newsSite: String,
    val summary: String,
    @SerialName("published_at")
    val publishedAt: String
)
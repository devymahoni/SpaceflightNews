package com.migros.spaceflightnews.domain.model

data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String?,
    val publishedAt: String,
    val newsSite: String,
    val articleUrl: String,
    val isFavorite: Boolean = false
)
package com.migros.spaceflightnews.feature.news.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ArticleDetailUiModel(
    val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String?,
    val newsSite: String,
    val publishedAt: String,
    val articleUrl: String,
    val isFavorite: Boolean
)
package com.migros.spaceflightnews.feature.news.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ArticleUiModel(
    val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String?,
    val metadata: String,
    val isFavorite: Boolean
)
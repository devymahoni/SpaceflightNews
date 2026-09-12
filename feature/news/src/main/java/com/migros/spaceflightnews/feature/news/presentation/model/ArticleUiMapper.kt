package com.migros.spaceflightnews.feature.news.presentation.model

import com.migros.spaceflightnews.domain.model.Article

fun Article.toUiModel(): ArticleUiModel {
    return ArticleUiModel(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        metadata = "$newsSite • $publishedAt",
        isFavorite = isFavorite
    )
}

fun Article.toDetailUiModel(): ArticleDetailUiModel {
    return ArticleDetailUiModel(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        newsSite = newsSite,
        publishedAt = publishedAt,
        articleUrl = articleUrl,
        isFavorite = isFavorite
    )
}
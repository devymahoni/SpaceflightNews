package com.migros.spaceflightnews.data.mapper

import com.migros.spaceflightnews.data.remote.dto.ArticleDto
import com.migros.spaceflightnews.domain.model.Article

fun ArticleDto.toDomain(isFavorite: Boolean = false): Article {
    return Article(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        newsSite = newsSite,
        articleUrl = url,
        isFavorite = isFavorite
    )
}
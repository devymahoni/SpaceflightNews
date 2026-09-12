package com.migros.spaceflightnews.data.mapper

import com.migros.spaceflightnews.data.local.entity.FavoriteArticleEntity
import com.migros.spaceflightnews.domain.model.Article

fun Article.toFavoriteEntity(): FavoriteArticleEntity {
    return FavoriteArticleEntity(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        newsSite = newsSite,
        articleUrl = articleUrl
    )
}

fun FavoriteArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        newsSite = newsSite,
        articleUrl = articleUrl,
        isFavorite = true
    )
}
package com.migros.spaceflightnews.domain.repository

import com.migros.spaceflightnews.domain.model.Article

interface NewsRepository {
    suspend fun getLatestArticles(): Result<List<Article>>

    suspend fun searchArticles(query: String): Result<List<Article>>

    suspend fun getFavoriteArticles(): Result<List<Article>>

    suspend fun toggleFavorite(article: Article): Result<Unit>
}
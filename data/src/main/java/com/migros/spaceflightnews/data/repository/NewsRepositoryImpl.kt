package com.migros.spaceflightnews.data.repository

import com.migros.spaceflightnews.data.mapper.toDomain
import com.migros.spaceflightnews.data.remote.api.SpaceflightNewsApi
import com.migros.spaceflightnews.domain.model.Article
import com.migros.spaceflightnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: SpaceflightNewsApi
) : NewsRepository {

    override suspend fun getLatestArticles(): Result<List<Article>> {
        return runCatching {
            api.getArticles().results.map { it.toDomain() }
        }
    }

    override suspend fun searchArticles(query: String): Result<List<Article>> {
        return runCatching {
            api.searchArticles(query = query).results.map { it.toDomain() }
        }
    }

    override suspend fun getFavoriteArticles(): Result<List<Article>> {
        return Result.success(emptyList())
    }

    override suspend fun toggleFavorite(article: Article): Result<Unit> {
        return Result.success(Unit)
    }
}
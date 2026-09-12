package com.migros.spaceflightnews.data.repository

import com.migros.spaceflightnews.data.local.dao.FavoriteArticleDao
import com.migros.spaceflightnews.data.mapper.toDomain
import com.migros.spaceflightnews.data.mapper.toFavoriteEntity
import com.migros.spaceflightnews.data.remote.api.SpaceflightNewsApi
import com.migros.spaceflightnews.domain.model.Article
import com.migros.spaceflightnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: SpaceflightNewsApi,
    private val favoriteArticleDao: FavoriteArticleDao
) : NewsRepository {

    override suspend fun getLatestArticles(): Result<List<Article>> {
        return runCatching {
            api.getArticles().results
                .map { it.toDomain() }
                .withFavoriteState()
        }
    }

    override suspend fun searchArticles(query: String): Result<List<Article>> {
        return runCatching {
            api.searchArticles(query = query).results
                .map { it.toDomain() }
                .withFavoriteState()
        }
    }

    override suspend fun getFavoriteArticles(): Result<List<Article>> {
        return runCatching {
            favoriteArticleDao.getFavoriteArticles().map { it.toDomain() }
        }
    }
    override suspend fun toggleFavorite(article: Article): Result<Unit> {
        return runCatching {
            if (favoriteArticleDao.isFavorite(article.id)) {
                favoriteArticleDao.deleteFavoriteArticle(article.toFavoriteEntity())
            } else {
                favoriteArticleDao.insertFavoriteArticle(article.toFavoriteEntity())
            }
        }
    }


    private suspend fun List<Article>.withFavoriteState(): List<Article> {
        val favoriteIds = favoriteArticleDao.getFavoriteArticleIds().toSet()
        return map { article ->
            article.copy(isFavorite = article.id in favoriteIds)
        }
    }
}
package com.migros.spaceflightnews.feature.news.testing

import com.migros.spaceflightnews.domain.model.Article
import com.migros.spaceflightnews.domain.repository.NewsRepository

class FakeNewsRepository : NewsRepository {

    var latestArticlesResult: Result<List<Article>> = Result.success(emptyList())
    var searchArticlesResult: Result<List<Article>> = Result.success(emptyList())
    var favoriteArticlesResult: Result<List<Article>> = Result.success(emptyList())
    var toggleFavoriteResult: Result<Unit> = Result.success(Unit)

    var latestArticlesCalled = false
    var searchQuery: String? = null
    var toggledArticle: Article? = null

    override suspend fun getLatestArticles(): Result<List<Article>> {
        latestArticlesCalled = true
        return latestArticlesResult
    }

    override suspend fun searchArticles(query: String): Result<List<Article>> {
        searchQuery = query
        return searchArticlesResult
    }

    override suspend fun getFavoriteArticles(): Result<List<Article>> {
        return favoriteArticlesResult
    }

    override suspend fun toggleFavorite(article: Article): Result<Unit> {
        toggledArticle = article
        return toggleFavoriteResult
    }
}
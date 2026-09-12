package com.migros.spaceflightnews.feature.news.presentation.list

import com.migros.spaceflightnews.domain.model.Article

data class NewsListUiState(
    val articles: List<Article> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: NewsListError? = null,
    val selectedArticle: Article? = null
)

sealed interface NewsListUiEvent {
    data object LoadArticles : NewsListUiEvent
    data class SearchQueryChanged(val query: String) : NewsListUiEvent
    data class ArticleClicked(val article: Article) : NewsListUiEvent
    data class FavoriteClicked(val article: Article) : NewsListUiEvent
    data object BackClicked : NewsListUiEvent
}

enum class NewsListError {
    LoadFailed,
    SearchFailed
}
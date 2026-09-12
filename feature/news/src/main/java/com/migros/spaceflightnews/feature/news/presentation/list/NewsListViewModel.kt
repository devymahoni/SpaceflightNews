package com.migros.spaceflightnews.feature.news.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migros.spaceflightnews.domain.model.Article
import com.migros.spaceflightnews.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsListUiState())
    val uiState: StateFlow<NewsListUiState> = _uiState.asStateFlow()

    fun onEvent(event: NewsListUiEvent) {
        when (event) {
            NewsListUiEvent.LoadArticles -> loadArticles()
            is NewsListUiEvent.SearchQueryChanged -> onSearchQueryChanged(event.query)
            is NewsListUiEvent.ArticleClicked -> onArticleClicked(event.article)
            is NewsListUiEvent.FavoriteClicked -> toggleFavorite(event.article)
            NewsListUiEvent.BackClicked -> onBackClicked()
        }
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            repository.getLatestArticles()
                .onSuccess { articles ->
                    _uiState.value = _uiState.value.copy(
                        articles = articles,
                        isLoading = false
                    )
                }
                .onFailure { _ ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = NewsListError.LoadFailed
                    )
                }
        }
    }

    private fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            val result = if (query.isBlank()) {
                repository.getLatestArticles()
            } else {
                repository.searchArticles(query)
            }

            result
                .onSuccess { articles ->
                    _uiState.value = _uiState.value.copy(
                        articles = articles,
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = NewsListError.SearchFailed
                    )
                }
        }
    }

    private fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            repository.toggleFavorite(article)
                .onSuccess {
                    val updatedArticle = article.copy(isFavorite = !article.isFavorite)

                    _uiState.value = _uiState.value.copy(
                        articles = _uiState.value.articles.map { currentArticle ->
                            if (currentArticle.id == article.id) updatedArticle else currentArticle
                        },
                        selectedArticle = _uiState.value.selectedArticle?.let { selectedArticle ->
                            if (selectedArticle.id == article.id) updatedArticle else selectedArticle
                        }
                    )
                }
        }
    }

    private fun onArticleClicked(article: Article) {
        _uiState.value = _uiState.value.copy(
            selectedArticle = article
        )
    }

    private fun onBackClicked() {
        _uiState.value = _uiState.value.copy(
            selectedArticle = null
        )
    }
}
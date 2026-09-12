package com.migros.spaceflightnews.feature.news.presentation.list

import com.migros.spaceflightnews.domain.model.Article
import com.migros.spaceflightnews.feature.news.testing.FakeNewsRepository
import com.migros.spaceflightnews.feature.news.testing.MainDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class NewsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val article = Article(
        id = 1,
        title = "Test title",
        summary = "Test summary",
        imageUrl = "https://example.com/image.jpg",
        publishedAt = "2026-09-11T10:00:00Z",
        newsSite = "Test site",
        articleUrl = "https://example.com/article"
    )

    @Test
    fun `load articles updates state with articles`() {
        val repository = FakeNewsRepository().apply {
            latestArticlesResult = Result.success(listOf(article))
        }
        val viewModel = NewsListViewModel(repository)

        viewModel.onEvent(NewsListUiEvent.LoadArticles)

        val state = viewModel.uiState.value
        assertEquals(listOf(article), state.articles)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
    }

    @Test
    fun `load articles updates state with load error when repository fails`() {
        val repository = FakeNewsRepository().apply {
            latestArticlesResult = Result.failure(IllegalStateException("Network error"))
        }
        val viewModel = NewsListViewModel(repository)

        viewModel.onEvent(NewsListUiEvent.LoadArticles)

        val state = viewModel.uiState.value
        assertEquals(emptyList<Article>(), state.articles)
        assertFalse(state.isLoading)
        assertEquals(NewsListError.LoadFailed, state.error)
    }

    @Test
    fun `search query changed updates state with search results`() {
        val repository = FakeNewsRepository().apply {
            searchArticlesResult = Result.success(listOf(article))
        }
        val viewModel = NewsListViewModel(repository)

        viewModel.onEvent(NewsListUiEvent.SearchQueryChanged("mars"))

        val state = viewModel.uiState.value
        assertEquals("mars", state.searchQuery)
        assertEquals(listOf(article), state.articles)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
        assertEquals("mars", repository.searchQuery)
    }

    @Test
    fun `search query changed shows empty state when repository fails`() {
        val repository = FakeNewsRepository().apply {
            searchArticlesResult = Result.failure(IllegalStateException("Search error"))
        }
        val viewModel = NewsListViewModel(repository)

        viewModel.onEvent(NewsListUiEvent.SearchQueryChanged("mars"))

        val state = viewModel.uiState.value
        assertEquals("mars", state.searchQuery)
        assertEquals(emptyList<Article>(), state.articles)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
        assertEquals("mars", repository.searchQuery)
    }
}
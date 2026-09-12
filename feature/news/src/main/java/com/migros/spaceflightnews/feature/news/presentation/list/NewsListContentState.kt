package com.migros.spaceflightnews.feature.news.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.migros.spaceflightnews.feature.news.R
import com.migros.spaceflightnews.feature.news.presentation.components.ArticleCard
import com.migros.spaceflightnews.feature.news.presentation.model.toUiModel

@Composable
internal fun NewsListContentState(
    uiState: NewsListUiState,
    onEvent: (NewsListUiEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp)
                )
            }

            uiState.error != null -> {
                NewsListMessage(
                    text = when (uiState.error) {
                        NewsListError.LoadFailed -> stringResource(R.string.news_list_error_load)
                        NewsListError.SearchFailed -> stringResource(R.string.news_list_error_search)
                    }
                )
            }

            uiState.articles.isEmpty() -> {
                NewsListMessage(
                    text = stringResource(R.string.news_list_empty)
                )
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = uiState.articles,
                        key = { article -> article.id }
                    ) { article ->
                        val articleUiModel = article.toUiModel()

                        ArticleCard(
                            article = articleUiModel,
                            onClick = {
                                onEvent(NewsListUiEvent.ArticleClicked(article))
                            },
                            onFavoriteClick = {
                                onEvent(NewsListUiEvent.FavoriteClicked(article))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NewsListMessage(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center
    )
}
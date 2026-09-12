package com.migros.spaceflightnews.feature.news.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.migros.spaceflightnews.feature.news.R
import com.migros.spaceflightnews.feature.news.presentation.detail.ArticleDetailScreen
import com.migros.spaceflightnews.feature.news.presentation.model.toDetailUiModel

@Composable
fun NewsListRoute(
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(NewsListUiEvent.LoadArticles)
    }

    val selectedArticle = uiState.selectedArticle

    if (selectedArticle != null) {
        ArticleDetailScreen(
            article = selectedArticle.toDetailUiModel(), onBackClick = {
                viewModel.onEvent(NewsListUiEvent.BackClicked)
            })
    } else {
        NewsListContent(
            uiState = uiState, onEvent = viewModel::onEvent
        )
    }
}

@Composable
private fun NewsListContent(
    uiState: NewsListUiState, onEvent: (NewsListUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.news_list_title),
                style = MaterialTheme.typography.headlineSmall
            )

            FilterChip(selected = uiState.showFavoritesOnly, onClick = {
                onEvent(NewsListUiEvent.FavoritesFilterClicked)
            }, label = {
                Text(text = stringResource(R.string.favorites_filter))
            })
        }
        Spacer(modifier = Modifier.height(12.dp))

        NewsSearchField(
            query = uiState.searchQuery,
            onQueryChange = { query ->
                onEvent(NewsListUiEvent.SearchQueryChanged(query))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        NewsListContentState(
            uiState = uiState, onEvent = onEvent
        )

    }
}
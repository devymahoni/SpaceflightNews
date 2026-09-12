package com.migros.spaceflightnews.feature.news.presentation.model

import com.migros.spaceflightnews.domain.model.Article
import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleUiMapperTest {

    private val article = Article(
        id = 1,
        title = "Test title",
        summary = "Test summary",
        imageUrl = "https://example.com/image.jpg",
        publishedAt = "2026-09-11T10:30:00Z",
        newsSite = "Test site",
        articleUrl = "https://example.com/article",
        isFavorite = true
    )

    @Test
    fun `to ui model formats metadata date`() {
        val uiModel = article.toUiModel()

        assertEquals("Test site • 11 Sep 2026, 10:30", uiModel.metadata)
    }

    @Test
    fun `to detail ui model formats published date`() {
        val uiModel = article.toDetailUiModel()

        assertEquals("11 Sep 2026, 10:30", uiModel.publishedAt)
    }

    @Test
    fun `to ui model keeps raw date when format is invalid`() {
        val invalidDateArticle = article.copy(
            publishedAt = "invalid-date"
        )

        val uiModel = invalidDateArticle.toUiModel()

        assertEquals("Test site • invalid-date", uiModel.metadata)
    }
}
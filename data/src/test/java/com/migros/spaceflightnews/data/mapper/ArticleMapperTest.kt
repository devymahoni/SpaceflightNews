package com.migros.spaceflightnews.data.mapper

import com.migros.spaceflightnews.data.remote.dto.ArticleDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ArticleMapperTest {

    @Test
    fun `toDomain maps article dto fields to article`() {
        val dto = ArticleDto(
            id = 1,
            title = "Test title",
            url = "https://example.com/article",
            imageUrl = "https://example.com/image.jpg",
            newsSite = "Test site",
            summary = "Test summary",
            publishedAt = "2026-09-11T10:00:00Z"
        )

        val article = dto.toDomain()

        assertEquals(1, article.id)
        assertEquals("Test title", article.title)
        assertEquals("https://example.com/article", article.articleUrl)
        assertEquals("https://example.com/image.jpg", article.imageUrl)
        assertEquals("Test site", article.newsSite)
        assertEquals("Test summary", article.summary)
        assertEquals("2026-09-11T10:00:00Z", article.publishedAt)
        assertFalse(article.isFavorite)
    }

    @Test
    fun `toDomain uses provided favorite state`() {
        val dto = ArticleDto(
            id = 1,
            title = "Test title",
            url = "https://example.com/article",
            imageUrl = null,
            newsSite = "Test site",
            summary = "Test summary",
            publishedAt = "2026-09-11T10:00:00Z"
        )

        val article = dto.toDomain(isFavorite = true)

        assertTrue(article.isFavorite)
    }
}
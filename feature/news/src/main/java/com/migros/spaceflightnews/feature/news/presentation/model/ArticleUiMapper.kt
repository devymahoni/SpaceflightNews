package com.migros.spaceflightnews.feature.news.presentation.model

import com.migros.spaceflightnews.domain.model.Article
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun Article.toUiModel(): ArticleUiModel {
    val formattedDate = publishedAt.toFormattedDate()

    return ArticleUiModel(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        metadata = "$newsSite • $formattedDate",
        isFavorite = isFavorite
    )
}

fun Article.toDetailUiModel(): ArticleDetailUiModel {
    return ArticleDetailUiModel(
        id = id,
        title = title,
        summary = summary,
        imageUrl = imageUrl,
        newsSite = newsSite,
        publishedAt = publishedAt.toFormattedDate(),
        articleUrl = articleUrl,
        isFavorite = isFavorite
    )
}

private fun String.toFormattedDate(): String {
    return runCatching {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val date = inputFormat.parse(this)
        requireNotNull(date)

        outputFormat.format(date)
    }.getOrElse {
        this
    }
}
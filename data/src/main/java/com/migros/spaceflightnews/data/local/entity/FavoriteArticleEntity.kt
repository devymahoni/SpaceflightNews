package com.migros.spaceflightnews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val summary: String,
    val imageUrl: String?,
    val publishedAt: String,
    val newsSite: String,
    val articleUrl: String
)
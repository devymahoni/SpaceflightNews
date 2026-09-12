package com.migros.spaceflightnews.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.migros.spaceflightnews.data.local.entity.FavoriteArticleEntity

@Dao
interface FavoriteArticleDao {

    @Query("SELECT * FROM favorite_articles ORDER BY publishedAt DESC")
    suspend fun getFavoriteArticles(): List<FavoriteArticleEntity>

    @Query("SELECT id FROM favorite_articles")
    suspend fun getFavoriteArticleIds(): List<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_articles WHERE id = :articleId)")
    suspend fun isFavorite(articleId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteArticle(article: FavoriteArticleEntity)

    @Delete
    suspend fun deleteFavoriteArticle(article: FavoriteArticleEntity)
}
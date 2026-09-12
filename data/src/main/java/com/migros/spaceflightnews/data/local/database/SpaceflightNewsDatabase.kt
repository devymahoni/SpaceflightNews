package com.migros.spaceflightnews.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.migros.spaceflightnews.data.local.dao.FavoriteArticleDao
import com.migros.spaceflightnews.data.local.entity.FavoriteArticleEntity

@Database(
    entities = [FavoriteArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpaceflightNewsDatabase : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}
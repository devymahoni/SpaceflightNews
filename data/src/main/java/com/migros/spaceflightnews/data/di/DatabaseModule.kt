package com.migros.spaceflightnews.data.di

import android.content.Context
import androidx.room.Room
import com.migros.spaceflightnews.data.local.dao.FavoriteArticleDao
import com.migros.spaceflightnews.data.local.database.SpaceflightNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSpaceflightNewsDatabase(
        @ApplicationContext context: Context
    ): SpaceflightNewsDatabase {
        return Room.databaseBuilder(
            context,
            SpaceflightNewsDatabase::class.java,
            "spaceflight_news.db"
        ).build()
    }

    @Provides
    fun provideFavoriteArticleDao(
        database: SpaceflightNewsDatabase
    ): FavoriteArticleDao {
        return database.favoriteArticleDao()
    }
}
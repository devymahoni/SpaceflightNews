package com.migros.spaceflightnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.migros.spaceflightnews.core.designsystem.SpaceflightNewsTheme
import com.migros.spaceflightnews.feature.news.presentation.list.NewsListRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceflightNewsTheme {
                NewsListRoute()
            }
        }
    }
}
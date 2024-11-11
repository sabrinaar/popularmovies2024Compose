package com.tmdb.popularmoviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tmdb.popularmoviescompose.ui.PopularMoviesApp
import dagger.hilt.android.AndroidEntryPoint


    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                PopularMoviesApp()
            }
        }
    }


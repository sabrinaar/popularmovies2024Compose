package com.tmdb.popularmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.tmdb.popularmoviescompose.ui.theme.MovieTheme


@Composable
fun PopularMoviesApp() {
    MovieTheme {
        val navController = rememberNavController()
        PopularMoviesNavGraph(
            navController = navController)
    }
}
package com.tmdb.popularmoviescompose.ui

sealed class Screen(val route: String) {
    object Home: Screen("home")
}
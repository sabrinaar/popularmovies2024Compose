package com.tmdb.popularmoviescompose.ui.home

import com.tmdb.popularmoviescompose.data.dto.Movie


data class HomeState(
    val movies: List<Movie> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val isLoading: Boolean = false
)

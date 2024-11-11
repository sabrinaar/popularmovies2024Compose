package com.tmdb.popularmoviescompose.data.api

import com.tmdb.popularmoviescompose.data.repositories.RepoMoviesImpl
import com.tmdb.popularmoviescompose.domain.RepoMovies
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindCharacterRepository(impl: RepoMoviesImpl): RepoMovies
}
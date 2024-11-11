package com.tmdb.popularmoviescompose.domain

import com.tmdb.popularmoviescompose.data.dto.ResponsePopularsMovie
import kotlinx.coroutines.flow.Flow
import com.tmdb.popularmoviescompose.vo.Resource
import javax.inject.Inject


class RepoImpl @Inject constructor(var repoMovies: RepoMovies) {
    operator fun invoke(page: Int): Flow<Resource<ResponsePopularsMovie>> {
        return repoMovies.getPopularMovies(page)
    }
}
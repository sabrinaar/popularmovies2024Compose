package com.tmdb.popularmoviescompose.domain

import com.tmdb.popularmoviescompose.data.dto.ResponsePopularsMovie
import kotlinx.coroutines.flow.Flow
import com.tmdb.popularmoviescompose.vo.Resource


interface RepoMovies {

    fun getPopularMovies(pageNum: Int): Flow<Resource<ResponsePopularsMovie>>

}

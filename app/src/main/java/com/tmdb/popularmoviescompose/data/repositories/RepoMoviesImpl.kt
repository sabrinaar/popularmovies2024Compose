package com.tmdb.popularmoviescompose.data.repositories

import com.tmdb.popularmoviescompose.data.api.MoviesApi
import com.tmdb.popularmoviescompose.data.dto.ResponsePopularsMovie
import com.tmdb.popularmoviescompose.domain.RepoMovies
import com.tmdb.popularmoviescompose.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RepoMoviesImpl @Inject constructor(
    var api: MoviesApi ): RepoMovies {


    override fun getPopularMovies(pageNum: Int): Flow<Resource<ResponsePopularsMovie>> =
        flow {
            try {
                val response = api.getPopularMovies(pageNum)
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Ups, algo salió mal",
                        data = null
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Error: check your internet connection",
                        data = null
                    )
                )
            }
        }
}
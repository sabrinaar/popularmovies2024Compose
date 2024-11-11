package com.tmdb.popularmoviescompose.data.api

import com.tmdb.popularmoviescompose.data.dto.ResponsePopularsMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): ResponsePopularsMovie

}
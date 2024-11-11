package com.tmdb.popularmoviescompose.data.dto

import com.google.gson.annotations.SerializedName


data class ResponsePopularsMovie (
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("results")
    var popularPelisList: MutableList<Movie>,
    @SerializedName("total_pages")
    var totalPaginas: Int,
    @SerializedName("total_results")
    var totalResultados: Int
)

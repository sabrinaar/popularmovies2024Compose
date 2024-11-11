package com.tmdb.popularmoviescompose.data.dto

import com.google.gson.annotations.SerializedName


data class Movie (
    val id: Int,
    @SerializedName("poster_path")
    var portada: String = "",
    @SerializedName("original_title")
    var titulo: String = "",
    @SerializedName("vote_average")
    var voto: Float
)

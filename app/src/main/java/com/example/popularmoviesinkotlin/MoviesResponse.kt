package com.example.popularmoviesinkotlin

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results") val movies: List<Movie>,
//    @SerializedName("page") val page: Int,
//    @SerializedName("total_pages") val totalPages: Int,

    )
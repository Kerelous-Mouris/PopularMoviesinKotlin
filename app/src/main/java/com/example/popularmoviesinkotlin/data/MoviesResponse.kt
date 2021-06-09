package com.example.popularmoviesinkotlin.data

import com.example.popularmoviesinkotlin.pojo.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("results") val movies: MutableList<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,

    )
package com.example.popularmoviesinkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "2e572b097b879578d69b00ce5691dc0e",
        @Query("page") pageNumber: Int = 1
    )
    : Call<MoviesResponse>

}
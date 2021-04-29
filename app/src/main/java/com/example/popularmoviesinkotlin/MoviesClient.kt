package com.example.popularmoviesinkotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MoviesClient {

    val services: ApiServices
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        services = retrofit.create(ApiServices::class.java)
    }

}
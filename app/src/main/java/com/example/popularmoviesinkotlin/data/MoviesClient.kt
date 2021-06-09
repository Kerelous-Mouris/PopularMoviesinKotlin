package com.example.popularmoviesinkotlin.data

import com.example.popularmoviesinkotlin.pojo.Movie
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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


    fun fetchPopularMovies(page: Int = 1,
                           onSuccess: (moviesList: MutableList<Movie>) -> Unit,
                           onError: () -> Unit
                           ){
        services.getPopularMovies("2e572b097b879578d69b00ce5691dc0e",page).enqueue(object :
            Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        onSuccess.invoke(response.body()!!.movies)
                    }else{
                        onError.invoke()
                    }

                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
            }
        })
    }

}
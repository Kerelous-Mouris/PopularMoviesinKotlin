package com.example.popularmoviesinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val movies: List<Movie>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPopularMovies()


    }

    fun getPopularMovies(){

        MoviesClient.services.getPopularMovies().enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful){

                    rv_movies.adapter = MoviesAdapter(response.body()?.movies)
                    rv_movies.layoutManager = LinearLayoutManager(applicationContext
                        , LinearLayoutManager.VERTICAL
                        ,false)

                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Failed to get Movies", Toast.LENGTH_SHORT).show()
            }

        })
    }
}
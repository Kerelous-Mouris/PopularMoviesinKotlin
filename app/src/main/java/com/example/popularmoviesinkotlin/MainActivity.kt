package com.example.popularmoviesinkotlin

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.i("level: ", "reached main activity right before get function")


        getPopularMovies()


    }

    fun getPopularMovies(){

        MoviesClient.services.getPopularMovies("2e572b097b879578d69b00ce5691dc0e").enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    Log.i("response","there is a response and it is successful")
                    rv_movies.adapter = MoviesAdapter(response.body()?.movies)
                    rv_movies.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
                }
                else{
                    Log.i("response","there is a response but not successful")
                    Log.i("response",response.toString())
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.i("response","Failed!")
            }
        })
    }
}
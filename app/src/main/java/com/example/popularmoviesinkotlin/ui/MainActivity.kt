package com.example.popularmoviesinkotlin.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesinkotlin.R
import com.example.popularmoviesinkotlin.data.MoviesClient
import com.example.popularmoviesinkotlin.pojo.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    var currentPageNumber: Int = 1
    lateinit var moviesAdapter : MoviesAdapter
    lateinit var llm : LinearLayoutManager
    lateinit var movieViewModel: MovieVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(mutableListOf())
        llm = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)

        rv_movies.adapter = moviesAdapter
        rv_movies.layoutManager = llm

        movieViewModel = ViewModelProvider(this).get(MovieVM::class.java)
        movieViewModel.initailize(currentPageNumber,applicationContext)
        movieViewModel.getPopularMovies()

        movieViewModel.moviesMutableLiveData.observe(this, object: Observer<List<Movie>>{
            override fun onChanged(t: List<Movie>?) {
                if (t != null) {
                    moviesAdapter.appendMovies(t)
                }
                movieViewModel.attachOnScrollListener(rv_movies,llm)
            }
        })


    }




    }



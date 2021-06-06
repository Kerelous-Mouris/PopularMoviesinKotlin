package com.example.popularmoviesinkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    var currentPageNumber: Int = 1
    lateinit var moviesAdapter : MoviesAdapter
    lateinit var llm : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(mutableListOf())
        llm = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)

        rv_movies.adapter = moviesAdapter
        rv_movies.layoutManager = llm

        getPopularMovies()


    }

    fun getPopularMovies(){

        MoviesClient.fetchPopularMovies(
            currentPageNumber,
        ::onPopularMoviesFetched,
        ::onError
        )
    }

    private fun onPopularMoviesFetched(moviesList: MutableList<Movie>) {
        moviesAdapter.appendMovies(moviesList)
        attachOnScrollListener()
    }


    fun attachOnScrollListener(){
        rv_movies.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = llm.itemCount
                val visibleItemCount = llm.childCount
                val firstVisibleItem = llm.findLastVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItems/2){
                    rv_movies.removeOnScrollListener(this)
                    currentPageNumber++
                    Log.i("page number", currentPageNumber.toString())
                    getPopularMovies()
                }
            }
        })
    }

    private fun onError() {
        Toast.makeText(this,"Failed to fetch movies", Toast.LENGTH_SHORT).show()
    }
}
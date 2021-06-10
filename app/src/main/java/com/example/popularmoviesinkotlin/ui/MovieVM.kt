package com.example.popularmoviesinkotlin.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmoviesinkotlin.data.MoviesClient
import com.example.popularmoviesinkotlin.pojo.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MovieVM() : ViewModel() {

    var moviesMutableLiveData : MutableLiveData<List<Movie>> = MutableLiveData()
    var currentPageNumber: Int = 1
    lateinit var context: Context

    fun initailize(page: Int, context: Context){
        currentPageNumber = page
        this.context = context
    }
    fun getPopularMovies()
    {

        MoviesClient.fetchPopularMovies(
            currentPageNumber,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun onError() {
        Toast.makeText(context,"Failed to fetch movies!", Toast.LENGTH_SHORT).show()
    }

    private fun onPopularMoviesFetched(mutableList: MutableList<Movie>) {
        moviesMutableLiveData.value = mutableList
    }

    fun attachOnScrollListener(rv_movies: RecyclerView, llm: LinearLayoutManager){
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
        })}
}



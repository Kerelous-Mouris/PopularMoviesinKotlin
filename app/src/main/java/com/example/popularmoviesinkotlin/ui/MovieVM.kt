package com.example.popularmoviesinkotlin.ui

import android.util.Log
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
    fun initailize(page: Int){
        currentPageNumber = page

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
        Log.i("Error","Failed to fetch movies")
    }

    private fun onPopularMoviesFetched(mutableList: MutableList<Movie>) {
        Log.i("success","movies fetched successfully!")
//        Log.i("Error",mutableList.toString())

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



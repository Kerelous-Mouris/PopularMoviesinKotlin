package com.example.popularmoviesinkotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.card_movie.view.*
class MoviesAdapter(private var moviesList: List<Movie>?) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: Movie){
            itemView.txt_release_date.text = movie.releaseDate
            itemView.txt_progress.text = movie.rating.toString()
            itemView.progressBar.progress = (movie.rating * 10).toInt()
            itemView.txt_title.text = movie.title

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .transform(CenterCrop())
                .into(itemView.movie_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.card_movie,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int){
        if (moviesList!!.isNotEmpty())
        {
        var movie = moviesList!![position]
        holder.onBind(movie)
        }
        else{
            Log.i("Adapter: ", "list is empty")
        }
    }

    override fun getItemCount(): Int {
        if (moviesList!!.isNotEmpty()){
            return moviesList!!.size
        }else{
            Log.i("adapter", "list is null in the adapter")
            return 0
        }
    }

}


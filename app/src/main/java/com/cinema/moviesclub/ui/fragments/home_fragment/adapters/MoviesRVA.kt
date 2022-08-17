package com.cinema.moviesclub.ui.fragments.home_fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cinema.moviesclub.R
import com.cinema.moviesclub.domain.model.Movie
import com.cinema.moviesclub.ui.fragments.home_fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.movies_rv_item.view.*

class MoviesRVA(
    private val moviesList: List<Movie>,
    private val view: View
) : RecyclerView.Adapter<MoviesRVA.MoviesViewHolder>() {

    private var options: RequestOptions =
        RequestOptions().centerCrop().placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_warning)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_rv_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        Glide.with(view.context).load(movie.image).apply(options).into(holder.movieImage)
        holder.movieTitle.text = movie.title
        holder.movieDirector.text = movie.director
        holder.movieActors.text = movie.actors
        holder.movieRating.text = movie.imdbRating
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.movie_item_image
        val movieTitle: TextView = itemView.movie_item_title_and_year_tv
        val movieDirector: TextView = itemView.movie_item_directors_content
        val movieActors: TextView = itemView.movie_item_actors_content
        val movieRating: TextView = itemView.movie_item_rating_tv

        init {
            val movieCard: CardView = itemView.movie_item_cv
            movieCard.setOnClickListener {
                if (moviesList[adapterPosition].id.isNotEmpty())
                    view.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMovieFragment(
                            moviesList[adapterPosition].id
                        )
                    )
            }
        }
    }
}
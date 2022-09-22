package com.cinema.moviesclub.ui.fragments.movie_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cinema.moviesclub.R
import com.cinema.moviesclub.databinding.FragmentMovieBinding
import com.cinema.moviesclub.ui.fragments.movie_fragment.view_model.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding
        get() = _fragmentMovieBinding!!

    private val movieViewModel: MovieViewModel by viewModels()
    private val args by navArgs<MovieFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = fragmentMovieBinding.root

        movieViewModel.getMovie(args.movieId)

        lifecycleScope.launchWhenCreated {
            movieViewModel.stateFlow.collectLatest { movieFragmentState ->

                val movieTitleAndYear =
                    movieFragmentState.movie.title + "(" + movieFragmentState.movie.year + ")"
                val movieRuntime = movieFragmentState.movie.runtime + " min"

                context?.let { context ->
                    fragmentMovieBinding.movieImageIv.let { imageView ->
                        Glide.with(context).load(movieFragmentState.movie.image).apply(
                            RequestOptions().centerCrop().placeholder(R.drawable.progress_animation)
                        ).into(imageView)

                        fragmentMovieBinding.movieTitleAndYearTv.text = movieTitleAndYear
                        fragmentMovieBinding.movieGenre.text =
                            movieFragmentState.movie.genre.replace(",", "")
                        fragmentMovieBinding.movieRatingTv.text =
                            movieFragmentState.movie.imdbRating
                        fragmentMovieBinding.movieDirectorsContent.text =
                            movieFragmentState.movie.director
                        fragmentMovieBinding.movieWritersContent.text =
                            movieFragmentState.movie.writer
                        fragmentMovieBinding.movieActorsContent.text =
                            movieFragmentState.movie.actors
                        fragmentMovieBinding.moviePlotTv.text = movieFragmentState.movie.plot
                        fragmentMovieBinding.movieRuntimeTv.text = movieRuntime
                        fragmentMovieBinding.ratingIcon.visibility = View.VISIBLE
                    }
                }
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMovieBinding = null
    }
}
package com.cinema.moviesclub.ui.fragments.movie_fragment.view_model

import com.cinema.moviesclub.domain.model.Movie

data class MovieState(
    val movie: Movie = Movie(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

package com.cinema.moviesclub.ui.fragments.home_fragment.view_model

import com.cinema.moviesclub.domain.model.Movie

data class HomeState(
    val topRatedMovies: List<Movie> = emptyList(),
    val oscarAwardedMovies: List<Movie> = emptyList(),
    val emmyAwardedMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

package com.cinema.moviesclub.domain.model

data class Movie(
    val id: String = "",
    val title: String = "",
    val year: String = "",
    val director: String = "",
    val writer: String = "",
    val genre: String = "",
    val runtime: String = "",
    val plot: String = "",
    val actors: String = "",
    val image: String = "",
    val imdbRating: String = ""
)

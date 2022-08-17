package com.cinema.moviesclub.domain.repository

import com.cinema.moviesclub.domain.model.Movie

interface MoviesClubRepo {

    suspend fun getMovieById(id: String): Movie

    suspend fun getMovieByTitle(title: String): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getOscarAwardedMovies(): List<Movie>

    suspend fun getEmmyAwardedMovies(): List<Movie>
}
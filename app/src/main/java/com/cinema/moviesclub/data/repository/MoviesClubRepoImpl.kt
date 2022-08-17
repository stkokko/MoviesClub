package com.cinema.moviesclub.data.repository

import com.cinema.moviesclub.data.remote.MoviesClubApi
import com.cinema.moviesclub.domain.model.Movie
import com.cinema.moviesclub.domain.repository.MoviesClubRepo
import com.cinema.moviesclub.util.Mappers
import javax.inject.Inject
import kotlin.Exception

class MoviesClubRepoImpl @Inject constructor(
    private val api: MoviesClubApi
) : MoviesClubRepo {

    override suspend fun getMovieById(id: String): Movie {
        return try {
            val movieDto = api.getMovieById(id)
            if (movieDto == null) {
                throw Exception("")
            } else {
                val movie = Mappers.movieDtoToModel(movieDto)
                movie
            }
        } catch (e: Exception) {
            Movie()
        }
    }

    override suspend fun getMovieByTitle(title: String): List<Movie> {
        return try {
            val searchMovieListDto = api.getMovieByTitle(title)
            if (searchMovieListDto?.searchMoviesDto.isNullOrEmpty()) {
                throw Exception("")
            } else {
                val searchMovie = searchMovieListDto?.searchMoviesDto?.map {
                    Mappers.searchMovieDtoToModel(it)
                }
                searchMovie ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return try {
            val topMoviesListDto = api.getTopRatedMovies()
            if (topMoviesListDto?.topRatedMoviesDto.isNullOrEmpty()) {
                throw Exception("")
            } else {
                val topMovies = topMoviesListDto?.topRatedMoviesDto?.map {
                    Mappers.topRatedMovieDtoToModel(it)
                }
                topMovies ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getOscarAwardedMovies(): List<Movie> {
        return try {
            val awardedMoviesListDto = api.getOscarAwardedMovies()
            if (awardedMoviesListDto?.awardedMoviesDto.isNullOrEmpty()) {
                throw Exception("")
            } else {
                val oscarAwardedMovies = awardedMoviesListDto?.awardedMoviesDto?.map {
                    Mappers.awardedMovieToModel(it)
                }
                oscarAwardedMovies ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getEmmyAwardedMovies(): List<Movie> {
        return try {
            val awardedMoviesListDto = api.getEmmyAwardedMovies()
            if (awardedMoviesListDto?.awardedMoviesDto.isNullOrEmpty()) {
                throw Exception("")
            } else {
                val emmyAwardedMovies = awardedMoviesListDto?.awardedMoviesDto?.map {
                    Mappers.awardedMovieToModel(it)
                }
                emmyAwardedMovies ?: emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
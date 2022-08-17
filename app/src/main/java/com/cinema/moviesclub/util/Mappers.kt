package com.cinema.moviesclub.util

import com.cinema.moviesclub.data.remote.dto.AwardedMovieDto
import com.cinema.moviesclub.data.remote.dto.MovieDto
import com.cinema.moviesclub.data.remote.dto.SearchMovieDto
import com.cinema.moviesclub.data.remote.dto.TopRatedMovieDto
import com.cinema.moviesclub.domain.model.Movie

object Mappers {

    fun movieDtoToModel(movieDto: MovieDto): Movie {
        return Movie(
            id = movieDto.id,
            title = movieDto.title,
            year = movieDto.year,
            director = movieDto.director,
            writer = movieDto.writer,
            actors = movieDto.actors,
            genre = movieDto.genre,
            runtime = movieDto.runtime,
            plot = movieDto.plot,
            image = movieDto.image,
            imdbRating = movieDto.imdbRating
        )
    }

    fun searchMovieDtoToModel(searchMovieDto: SearchMovieDto): Movie {
        return Movie(
            id = searchMovieDto.id.trim(),
            title = searchMovieDto.title.trim(),
            year = searchMovieDto.year.substring(1, 5).trim(),
            image = searchMovieDto.image.trim()
        )
    }

    fun topRatedMovieDtoToModel(topRatedMovieDto: TopRatedMovieDto): Movie {
        return Movie(
            id = topRatedMovieDto.id.trim(),
            title = topRatedMovieDto.title.trim(),
            year = topRatedMovieDto.year.trim(),
            director = topRatedMovieDto.crew.substringBefore("(").trim(),
            actors = topRatedMovieDto.crew.substringAfter(",").trim(),
            image = topRatedMovieDto.image.trim(),
            imdbRating = topRatedMovieDto.imdbRating.trim()
        )
    }

    fun awardedMovieToModel(awardedMovieDto: AwardedMovieDto): Movie {
        return Movie(
            id = awardedMovieDto.id.trim(),
            title = awardedMovieDto.title.trim(),
            year = if (awardedMovieDto.year.contains("I")) awardedMovieDto.year.substringAfter(
                ")"
            ).trim() else awardedMovieDto.year.trim(),
            director = awardedMovieDto.stars.substringBefore(",").trim(),
            actors = awardedMovieDto.stars.substringAfter(",").trim(),
            image = awardedMovieDto.image.trim(),
            imdbRating = awardedMovieDto.imdbRating.trim()
        )
    }
}
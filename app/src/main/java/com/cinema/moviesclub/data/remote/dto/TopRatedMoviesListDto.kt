package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesListDto(
    @SerializedName("items")
    val _topRatedMoviesDto: List<TopRatedMovieDto>?
) {
    val topRatedMoviesDto: List<TopRatedMovieDto>
        get() = _topRatedMoviesDto ?: emptyList()
}

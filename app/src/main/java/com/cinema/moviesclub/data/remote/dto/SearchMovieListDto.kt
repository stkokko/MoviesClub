package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchMovieListDto(
    @SerializedName("results")
    val _searchMoviesDto: List<SearchMovieDto>?
) {
    val searchMoviesDto: List<SearchMovieDto>
        get() = _searchMoviesDto ?: emptyList()
}

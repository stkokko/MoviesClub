package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AwardedMoviesListDto(
    @SerializedName("results")
    val _awardedMoviesDto: List<AwardedMovieDto>?
) {
    val awardedMoviesDto: List<AwardedMovieDto>
        get() = _awardedMoviesDto ?: emptyList()
}

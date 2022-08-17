package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TopRatedMovieDto(
    @SerializedName("id")
    val _id: String? = "",
    @SerializedName("title")
    val _title: String? = "",
    @SerializedName("year")
    val _year: String? = "",
    @SerializedName("crew")
    val _crew: String? = "",
    @SerializedName("image")
    val _image: String? = "",
    @SerializedName("imDbRating")
    val _imdbRating: String? = ""
) {
    val id: String
        get() = _id ?: ""

    val title: String
        get() = _title ?: ""

    val year: String
        get() = _year ?: ""

    val crew: String
        get() = _crew ?: ""

    val image: String
        get() = _image ?: ""

    val imdbRating: String
        get() = _imdbRating ?: ""
}

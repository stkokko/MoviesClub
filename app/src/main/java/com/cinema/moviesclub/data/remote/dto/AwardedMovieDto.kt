package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AwardedMovieDto(
    @SerializedName("id")
    val _id: String? = "",
    @SerializedName("title")
    val _title: String? = "",
    @SerializedName("description")
    val _year: String? = "",
    @SerializedName("stars")
    val _stars: String? = "",
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

    val stars: String
        get() = _stars ?: ""

    val image: String
        get() = _image ?: ""

    val imdbRating: String
        get() = _imdbRating ?: ""
}

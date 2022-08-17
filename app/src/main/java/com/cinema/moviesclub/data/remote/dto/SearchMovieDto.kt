package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchMovieDto(
    @SerializedName("id")
    val _id: String? = "",
    @SerializedName("title")
    val _title: String? = "",
    @SerializedName("description")
    val _year: String? = "",
    @SerializedName("image")
    val _image: String? = "",

) {
    val id: String
        get() = _id ?: ""

    val title: String
        get() = _title ?: ""

    val year: String
        get() = _year ?: ""

    val image: String
        get() = _image ?: ""
}

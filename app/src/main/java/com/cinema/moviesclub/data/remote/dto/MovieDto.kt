package com.cinema.moviesclub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val _id: String? = "",
    @SerializedName("title")
    val _title: String? = "",
    @SerializedName("year")
    val _year: String? = "",
    @SerializedName("directors")
    val _director: String? = "",
    @SerializedName("writers")
    val _writer: String? = "",
    @SerializedName("stars")
    val _actors: String? = "",
    @SerializedName("image")
    val _image: String? = "",
    @SerializedName("plot")
    val _plot: String? = "",
    @SerializedName("genres")
    val _genre: String? = "",
    @SerializedName("runtimeMins")
    val _runtime: String? = "",
    @SerializedName("imDbRating")
    val _imdbRating: String? = ""
) {
    val id: String
        get() = _id ?: ""

    val title: String
        get() = _title ?: ""

    val year: String
        get() = _year ?: ""

    val director: String
        get() = _director ?: ""

    val writer: String
        get() = _writer ?: ""

    val actors: String
        get() = _actors ?: ""

    val image: String
        get() = _image ?: ""

    val plot: String
        get() = _plot ?: ""

    val genre: String
        get() = _genre ?: ""

    val runtime: String
        get() = _runtime ?: ""

    val imdbRating: String
        get() = _imdbRating ?: ""

}

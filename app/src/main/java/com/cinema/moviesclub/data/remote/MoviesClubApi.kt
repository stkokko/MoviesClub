package com.cinema.moviesclub.data.remote

import com.cinema.moviesclub.data.remote.dto.AwardedMoviesListDto
import com.cinema.moviesclub.data.remote.dto.MovieDto
import com.cinema.moviesclub.data.remote.dto.SearchMovieListDto
import com.cinema.moviesclub.data.remote.dto.TopRatedMoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesClubApi {

    @GET("Title/k_vtxgm52d/{id}")
    suspend fun getMovieById(
        @Path("id") id: String
    ): MovieDto?

    @GET("SearchMovie/k_vtxgm52d/{title}")
    suspend fun getMovieByTitle(
        @Path("title") title: String
    ): SearchMovieListDto?

    @GET("Top250Movies/k_vtxgm52d")
    suspend fun getTopRatedMovies(): TopRatedMoviesListDto?

    @GET("AdvancedSearch/k_vtxgm52d?title_type=feature&groups=oscar_winners")
    suspend fun getOscarAwardedMovies(): AwardedMoviesListDto?

    @GET("AdvancedSearch/k_vtxgm52d?title_type=feature&groups=emmy_winners")
    suspend fun getEmmyAwardedMovies(): AwardedMoviesListDto?
}
package com.cinema.moviesclub.di

import com.cinema.moviesclub.data.remote.MoviesClubApi
import com.cinema.moviesclub.data.repository.MoviesClubRepoImpl
import com.cinema.moviesclub.domain.repository.MoviesClubRepo
import com.cinema.moviesclub.domain.use_cases.GetEmmyAwardedMovies
import com.cinema.moviesclub.domain.use_cases.GetOscarAwardedMovies
import com.cinema.moviesclub.domain.use_cases.GetTopRatedMovies
import com.cinema.moviesclub.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMoviesClubApi(): MoviesClubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesClubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesClubRepo(
        api: MoviesClubApi
    ): MoviesClubRepo {
        return MoviesClubRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetTopMoviesUseCase(moviesClubRepo: MoviesClubRepo): GetTopRatedMovies {
        return GetTopRatedMovies(moviesClubRepo)
    }

    @Provides
    @Singleton
    fun provideGetOscarAwardedMoviesUseCase(moviesClubRepo: MoviesClubRepo): GetOscarAwardedMovies {
        return GetOscarAwardedMovies(moviesClubRepo)
    }

    @Provides
    @Singleton
    fun provideGetEmmyAwardedMoviesUseCase(moviesClubRepo: MoviesClubRepo): GetEmmyAwardedMovies {
        return GetEmmyAwardedMovies(moviesClubRepo)
    }
}
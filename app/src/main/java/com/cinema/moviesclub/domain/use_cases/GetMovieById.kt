package com.cinema.moviesclub.domain.use_cases

import com.cinema.moviesclub.domain.model.Movie
import com.cinema.moviesclub.domain.repository.MoviesClubRepo
import com.cinema.moviesclub.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val moviesClubRepo: MoviesClubRepo
) {
    operator fun invoke(id: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        val result = moviesClubRepo.getMovieById(id)
        if (result.id.isNotEmpty())
            emit(Resource.Success(result))
        else
            emit(Resource.Error(message = "No data retrieved from Api."))
    }.flowOn(Dispatchers.IO)
}
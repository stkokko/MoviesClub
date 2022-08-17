package com.cinema.moviesclub.ui.fragments.movie_fragment.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.moviesclub.domain.model.Movie
import com.cinema.moviesclub.domain.use_cases.GetMovieById
import com.cinema.moviesclub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieById: GetMovieById
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(MovieState())
    val stateFlow = _stateFlow.asStateFlow()

    fun getMovie(movieId: String) {
        getMovieById(movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        movie = Movie(),
                        isLoading = true,
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        movie = result.data ?: Movie(),
                        isLoading = false,
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        movie = Movie(),
                        isLoading = false,
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.cinema.moviesclub.ui.fragments.home_fragment.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.moviesclub.domain.use_cases.GetEmmyAwardedMovies
import com.cinema.moviesclub.domain.use_cases.GetOscarAwardedMovies
import com.cinema.moviesclub.domain.use_cases.GetTopRatedMovies
import com.cinema.moviesclub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getOscarAwardedMovies: GetOscarAwardedMovies,
    private val getEmmyAwardedMovies: GetEmmyAwardedMovies
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(HomeState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        handleRequestGetTopRatedMovies()
    }

    fun handleRequestGetTopRatedMovies() {
        getTopRatedMovies().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = true,
                        topRatedMovies = emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        topRatedMovies = result.data ?: emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        topRatedMovies = emptyList(),
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun handleRequestGetOscarAwardedMovies() {
        getOscarAwardedMovies().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = true,
                        oscarAwardedMovies = emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        oscarAwardedMovies = result.data ?: emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        oscarAwardedMovies = emptyList(),
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun handleRequestGetEmmyAwardedMovies() {
        getEmmyAwardedMovies().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = true,
                        emmyAwardedMovies = emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        emmyAwardedMovies = result.data ?: emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _stateFlow.value = _stateFlow.value.copy(
                        isLoading = false,
                        emmyAwardedMovies = emptyList(),
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.tmdb.popularmoviescompose.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.popularmoviescompose.domain.RepoMovies
import com.tmdb.popularmoviescompose.ui.home.HomeState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.tmdb.popularmoviescompose.vo.Resource
import kotlinx.coroutines.flow.launchIn
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (var repoMovies: RepoMovies) : ViewModel() {

    var state by mutableStateOf(HomeState(isLoading = true))
        private set

    private val _moviesFlow = MutableSharedFlow<UIEvent>()
    val moviesFlow = _moviesFlow.asSharedFlow()

    private var currentPage = 1

    init {
        getPopularMovies(increase = false)
    }

    fun getPopularMovies(increase: Boolean) {
        viewModelScope.launch {
            if (increase) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage < 42
            repoMovies.getPopularMovies(currentPage).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            movies = result.data?.popularPelisList ?: emptyList(),
                            isLoading = false,
                            showPrevious = showPrevious,
                            showNext = showNext
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _moviesFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}

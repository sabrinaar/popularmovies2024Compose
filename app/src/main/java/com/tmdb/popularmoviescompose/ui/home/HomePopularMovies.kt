package com.tmdb.popularmoviescompose.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.popularmoviescompose.R
import com.tmdb.popularmoviescompose.data.dto.Movie
import com.tmdb.popularmoviescompose.ui.home.components.MovieItem
import com.tmdb.popularmoviescompose.ui.home.viewmodel.MainViewModel
import com.tmdb.popularmoviescompose.ui.theme.Navy
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomePopularMovies(
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val eventFlow = viewModel.moviesFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is MainViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar(
                showPrevious = state.showPrevious,
                showNext = state.showNext,
                onPreviousPressed = { viewModel.getPopularMovies(false) },
                onNextPressed = { viewModel.getPopularMovies(true) }
            )
        }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            isLoading = state.isLoading,
            movies = state.movies
        )
    }
}

@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = Navy
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    movies: List<Movie> = emptyList()
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Navy
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(movies.size) { index ->
                MovieItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = movies[index])
            }

        }
        if (isLoading) FullScreenLoading()
    }
}

@Composable
private fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Navy)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón de "Anterior"
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showPrevious,
                onClick = onPreviousPressed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.back_button),
                    modifier = Modifier.size(24.dp)
                )
            }

            // Botón de "Siguiente"
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showNext,
                onClick = onNextPressed
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = stringResource(id = R.string.next_button),
                    modifier = Modifier.size(24.dp) // Tamaño del ícono
                )
            }
        }
    }
}



@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

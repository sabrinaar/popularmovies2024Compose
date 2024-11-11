package com.tmdb.popularmoviescompose.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.tmdb.popularmoviescompose.data.dto.Movie
import com.tmdb.popularmoviescompose.util.BASE_URL_IMAGE_PORTADA


@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    item: Movie
) {
    Column(
        modifier = modifier
            .padding(start = 6.dp, top = 12.dp, bottom = 12.dp)
            .fillMaxWidth() // Aseguramos que la columna ocupe el ancho completo
    ) {
        // Contenedor de la imagen
        MovieImageContainer(modifier = Modifier.size(128.dp)) {
            MovieImage(item) // Aquí se muestra la imagen
        }

        Spacer(modifier = Modifier.height(8.dp)) // Espacio entre la imagen y el texto

        // Contenedor para el texto
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Título de la película
            Text(
                text = item.titulo,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )

            // Voto de la película
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${item.voto}",
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MovieImage(item: Movie) {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("$BASE_URL_IMAGE_PORTADA${item.portada}")
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MovieImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), shape = RoundedCornerShape(4.dp)) {
        content()
    }
}

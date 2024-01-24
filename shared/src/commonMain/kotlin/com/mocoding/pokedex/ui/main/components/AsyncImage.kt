package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter


@Composable
internal fun AsyncImage(
    url: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    val imagePainter = rememberAsyncImagePainter(url, contentScale = contentScale)
    Box(modifier, Alignment.Center) {
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            colorFilter = colorFilter,
            modifier = Modifier.matchParentSize(),
        )
        when (imagePainter.state) {
            is AsyncImagePainter.State.Error -> {
                Text((imagePainter.state as AsyncImagePainter.State.Error).result.throwable.message.orEmpty())
            }

            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Success -> Unit
        }
    }
}
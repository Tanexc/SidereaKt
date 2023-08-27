package ru.tanec.siderakt.presentation.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun Picture(
    modifier: Modifier = Modifier,
    imageURL: String,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageURL,
        contentDescription = "",
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading ->
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxHeight()
                            .padding(8.dp)
                    )
                }

            is AsyncImagePainter.State.Success ->
                SubcomposeAsyncImageContent(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillWidth
                )

            else ->
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Icon(
                        Icons.Default.Error,
                        null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
        }
    }
}
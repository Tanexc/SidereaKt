package ru.tanec.siderakt.presentation.catalog.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import ru.tanec.siderakt.domain.model.Constellation

@Composable
fun ConstellationItem(
    modifier: Modifier = Modifier,
    constellation: Constellation
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(modifier = modifier) {
            Column(modifier = modifier) {
                SubcomposeAsyncImage(
                    model = constellation.imageURL,
                    contentDescription = "",
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading ->
                            CircularProgressIndicator()

                        is AsyncImagePainter.State.Success ->
                            SubcomposeAsyncImageContent()

                        else ->
                            Icon(
                                Icons.Default.Error,
                                ""
                            )

                    }
                }
            }
        }
    }
}
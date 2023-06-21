package ru.tanec.siderakt.presentation.catalog.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import ru.tanec.siderakt.domain.model.Constellation

@Composable
fun ConstellationItem(
    modifier: Modifier = Modifier,
    constellation: Constellation,
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 16.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(0.3f),
    height: Dp = 256.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .border(
                width = 0.dp,
                shape = RoundedCornerShape(borderRadius),
                brush = Brush.horizontalGradient(listOf(borderColor.copy(0f), borderColor.copy(0f)))
            )
            .background(backgroundColor, RoundedCornerShape(borderRadius))
            .fillMaxWidth()
            .height(height)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = constellation.imageURL,
                contentDescription = "",
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading ->
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .height(48.dp)
                        )

                    is AsyncImagePainter.State.Success ->
                        SubcomposeAsyncImageContent(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(2.dp)
                                .fillMaxHeight(0.8f)
                                .clip(RoundedCornerShape(borderRadius)),
                            contentScale = ContentScale.FillWidth

                        )

                    else ->
                        Icon(
                            Icons.Default.Error,
                            null,
                            modifier = Modifier.align(Alignment.Center)
                        )

                }
            }
            Row(modifier = Modifier.fillMaxSize()) {
                Text(
                    constellation.title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterVertically),
                    fontSize = 22.sp
                )
            }

        }
    }
}
package ru.tanexc.siderakt.presentation.utils.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tanexc.siderakt.domain.model.Constellation

@Composable
fun ConstellationItem(
    modifier: Modifier = Modifier,
    constellation: Constellation,
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 16.dp,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(0.3f),
    height: Dp = 248.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .border(
                width = borderWidth,
                shape = RoundedCornerShape(borderRadius),
                brush = SolidColor(borderColor)
            )
            .background(backgroundColor, RoundedCornerShape(borderRadius))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Picture(
                modifier = Modifier.fillMaxWidth().height(height),
                imageURL = constellation.imageURL,
                contentDescription = "",
            )
            Row(modifier = Modifier.fillMaxSize().padding(8.dp, 12.dp)) {
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
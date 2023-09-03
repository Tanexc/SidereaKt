package ru.tanexc.siderakt.presentation.utils.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    borderRadius: Dp = 16.dp,
    borderColor: Color = Color.Transparent,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(0.3f),
    content: @Composable () -> Unit

) {
    Box(
        modifier = modifier
            .border(
                width = borderWidth,
                shape = RoundedCornerShape(borderRadius),
                brush = SolidColor(borderColor)
            )
            .background(backgroundColor, RoundedCornerShape(borderRadius))
    ) {
        content()
    }
}
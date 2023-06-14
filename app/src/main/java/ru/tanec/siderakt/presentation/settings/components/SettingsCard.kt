package ru.tanec.siderakt.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SettingsCard(
    modifier: Modifier,
    borderWidth: Dp = 0.dp,
    borderRadius: Dp = 0.dp,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary.copy(0.3f),
    content: @Composable () -> Unit

) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .border(
                width = borderWidth,
                shape = RoundedCornerShape(borderRadius),
                brush = Brush.horizontalGradient(listOf(borderColor, borderColor))
            )
            .background(backgroundColor, RoundedCornerShape(borderRadius))
    ) {
        content()
    }
}
package ru.tanec.siderakt.domain.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.unit.Dp
import ru.tanec.siderakt.core.util.Scheme

data class Settings(
    var appTheme: Scheme,
    var colorScheme: ColorScheme,
    var useDarkTheme: Boolean,
    var borderWidth: Dp,
    )
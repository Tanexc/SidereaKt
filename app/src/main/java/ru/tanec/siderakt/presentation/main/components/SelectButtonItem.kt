package ru.tanec.siderakt.presentation.main.components

import android.service.autofill.OnClickAction
import androidx.compose.runtime.Composable


data class SelectButtonItem(
    val title: String,
    val onSelected: () -> Unit
)
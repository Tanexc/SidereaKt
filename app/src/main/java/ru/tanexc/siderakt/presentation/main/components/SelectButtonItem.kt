package ru.tanexc.siderakt.presentation.main.components


data class SelectButtonItem(
    val title: String,
    val onSelected: () -> Unit
)
package ru.tanec.siderakt.presentation.main.components


data class SelectButtonItem(
    val title: String,
    val onSelected: () -> Unit
)
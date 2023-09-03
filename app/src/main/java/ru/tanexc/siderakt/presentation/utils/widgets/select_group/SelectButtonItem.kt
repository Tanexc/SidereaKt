package ru.tanexc.siderakt.presentation.utils.widgets.select_group


data class SelectButtonItem(
    val title: String,
    val onSelected: () -> Unit
)
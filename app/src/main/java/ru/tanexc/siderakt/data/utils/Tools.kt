package ru.tanexc.siderakt.data.utils

fun Int.toBoolean(): Boolean {
    return when(this) {
        1 -> true
        else -> false
    }
}
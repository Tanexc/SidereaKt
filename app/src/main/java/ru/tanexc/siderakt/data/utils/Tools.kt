package ru.tanexc.siderakt.data.utils

fun Int.toBoolean(): Boolean {
    return when(this) {
        1 -> true
        else -> false
    }
}

fun Boolean.toInt(): Int {
    return when(this) {
        true -> 1
        else -> 0
    }
}
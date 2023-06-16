package ru.tanec.siderakt.core.util

sealed class State<T>(val data: T?, val message: String? = null) {
    class Success<T>(data: T?) : State<T>(data)
    class Error<T>(data: T? = null, message: String? = null) : State<T>(data, message)
    class Loading<T>(data: T? = null) : State<T>(data)
}
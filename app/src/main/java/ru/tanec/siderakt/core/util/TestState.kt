package ru.tanec.siderakt.core.util

sealed class TestState {
    data object Started : TestState()
    data object Ended : TestState()
    data object NotStarted : TestState()
}
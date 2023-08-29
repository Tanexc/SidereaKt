package ru.tanec.siderakt.core.util.state

sealed class TestState {
    data object Started : TestState()
    data object Ended : TestState()
    data object NotStarted : TestState()
}
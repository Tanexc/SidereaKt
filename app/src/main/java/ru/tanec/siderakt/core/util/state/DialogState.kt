package ru.tanec.siderakt.core.util.state

sealed class DialogState {
    data object Exit: DialogState()
    data object OpenLink: DialogState()
    data object FinishTest: DialogState()
    data object TestInfo: DialogState()
}
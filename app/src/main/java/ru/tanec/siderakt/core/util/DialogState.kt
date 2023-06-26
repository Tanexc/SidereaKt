package ru.tanec.siderakt.core.util

sealed class DialogState {

    object Exit: DialogState()

    object OpenLink: DialogState()

    object FinishTest: DialogState()

}
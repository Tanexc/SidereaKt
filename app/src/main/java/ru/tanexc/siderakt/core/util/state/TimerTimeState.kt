package ru.tanexc.siderakt.core.util.state


sealed class TimerTimeState(val time: Int) {
    data object Short: TimerTimeState(time = 120)
    data object SemiShort: TimerTimeState(time = 180)
    data object Medium: TimerTimeState(time = 300)
    data object Large: TimerTimeState(time = 600)
}
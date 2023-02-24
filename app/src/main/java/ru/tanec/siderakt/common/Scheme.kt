package ru.tanec.siderakt.common

sealed class Scheme(val id: Int) {
    class Default: Scheme(id=0)
    class Blue: Scheme(id=1)
    class Green: Scheme(id=2)
}
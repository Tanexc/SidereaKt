package ru.tanec.siderakt.common

sealed class Scheme(val id: Int) {
    class System(): Scheme(id=0)
    class Dark(): Scheme(id=1)
    class Light(): Scheme(id=2)
    class Blue(): Scheme(id=3)
}
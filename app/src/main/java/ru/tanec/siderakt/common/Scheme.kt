package ru.tanec.siderakt.common

sealed class Scheme(val id: Int) {
    class Default: Scheme(id=0)
    class Blue: Scheme(id=1)
    class Green: Scheme(id=2)

    companion object {
        fun getScheme(id: Int)
                = when (id) {
            0 -> Default()
            1 -> Blue()
            2 -> Green()
            else -> Default()
        }
    }
}
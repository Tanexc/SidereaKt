package ru.tanexc.siderakt.core.util

sealed class Theme(val id: Int) {
    class Default: Theme(id=0)
    class Orange: Theme(id=1)
    class Green: Theme(id=2)

    companion object {
        fun getScheme(id: Int)
                = when (id) {
            0 -> Default()
            1 -> Orange()
            2 -> Green()
            else -> Default()
        }
    }
}
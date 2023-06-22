package ru.tanec.siderakt.core.util

sealed class Theme(val id: Int) {
    class Default: Theme(id=0)
    class Blue: Theme(id=1)
    class Green: Theme(id=2)

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
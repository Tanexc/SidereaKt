package ru.tanexc.siderakt.core.util

import ru.tanexc.siderakt.R

sealed class Theme(val id: Int, val label: Int) {
    class Default: Theme(id=0, label = R.string.blue)
    class Orange: Theme(id=1, label = R.string.orange)
    class Green: Theme(id=2, label = R.string.green)

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
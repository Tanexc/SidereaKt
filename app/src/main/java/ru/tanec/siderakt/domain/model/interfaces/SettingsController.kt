package ru.tanec.siderakt.domain.model.interfaces

import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.SettingsData

interface SettingsController {
    val data: SettingsData?

    fun updateSettingsData(data: SettingsData)

    fun theme(): Theme?

    fun learnedNorth(): Int

    fun learnedSouth(): Int

    fun isThemeInDarkMode(): Boolean

}
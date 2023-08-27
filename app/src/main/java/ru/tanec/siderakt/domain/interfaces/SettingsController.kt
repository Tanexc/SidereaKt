package ru.tanec.siderakt.domain.interfaces

import androidx.compose.material3.ColorScheme
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.SettingsData

interface SettingsController {
    val data: SettingsData?
    val colorScheme: ColorScheme
    fun updateSettingsData(data: SettingsData)

    fun theme(): Theme?

    fun learnedNorth(): Int

    fun learnedSouth(): Int

    fun isThemeInDarkMode(): Boolean
}
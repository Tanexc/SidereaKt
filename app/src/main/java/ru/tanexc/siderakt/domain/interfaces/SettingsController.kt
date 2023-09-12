package ru.tanexc.siderakt.domain.interfaces

import androidx.compose.material3.ColorScheme
import ru.tanexc.siderakt.core.util.Theme
import ru.tanexc.siderakt.domain.model.SettingsData

interface SettingsController {
    val data: SettingsData?
    val colorScheme: ColorScheme

    fun updateSettingsData(data: SettingsData)

    fun theme(): Theme?

    fun learnedNorth(): Int

    fun learnedSouth(): Int

    fun learnedEquatorial(): Int

    fun learned(): Int

    fun isThemeInDarkMode(): Boolean

    fun isOutlineElements(): Boolean

    fun isMarkLearned(): Boolean
}
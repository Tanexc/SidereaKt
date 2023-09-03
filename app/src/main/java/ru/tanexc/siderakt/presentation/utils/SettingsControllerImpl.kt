package ru.tanexc.siderakt.presentation.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import ru.tanexc.siderakt.presentation.ui.theme.getTheme
import ru.tanexc.siderakt.core.util.Theme
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import ru.tanexc.siderakt.domain.model.SettingsData

class SettingsControllerImpl : SettingsController {

    private val _data: MutableState<SettingsData?> = mutableStateOf(null)
    override val data by _data

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(getTheme(theme(), isThemeInDarkMode()))
    override val colorScheme by _colorScheme

    override fun updateSettingsData(data: SettingsData) {
        _data.value = data
        _colorScheme.value = getTheme(theme(), isThemeInDarkMode())
    }

    override fun theme(): Theme = data?.selectedTheme?: Theme.Default()

    override fun learnedNorth(): Int = data?.learnedNorth?: 0

    override fun learnedSouth(): Int = data?.learnedSouth?: 0

    override fun learnedEquatorial(): Int = data?.learnedEquatorial?: 0

    override fun isThemeInDarkMode(): Boolean = data?.useDarkTheme?: true

}
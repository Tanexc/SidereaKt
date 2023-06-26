package ru.tanec.siderakt.presentation.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import ru.tanec.siderakt.core.util.DialogState
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.SettingsData
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.presentation.ui.theme.getTheme

class SettingsControllerImpl : SettingsController {

    private val _data: MutableState<SettingsData?> = mutableStateOf(null)
    override val data by _data

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(getTheme(theme(), isThemeInDarkMode()))
    override val colorScheme by _colorScheme

    override fun updateSettingsData(data: SettingsData) {
        _data.value = data
        _colorScheme.value = getTheme(theme(), isThemeInDarkMode())
    }

    override fun theme() = data?.selectedTheme?: Theme.Default()

    override fun learnedNorth() = data?.learnedNorth?: 0

    override fun learnedSouth() = data?.learnedSouth?: 0

    override fun isThemeInDarkMode() = data?.useDarkTheme?: true

}
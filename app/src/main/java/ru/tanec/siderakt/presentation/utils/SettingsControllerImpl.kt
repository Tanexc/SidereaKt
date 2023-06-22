package ru.tanec.siderakt.presentation.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import ru.tanec.siderakt.domain.model.SettingsData
import ru.tanec.siderakt.domain.model.interfaces.SettingsController

class SettingsControllerImpl: SettingsController {

    private val _data: MutableState<SettingsData?> = mutableStateOf(null)
    override val data by _data

    override fun updateSettingsData(data: SettingsData) {
        _data.value = data
    }

    override fun theme() = data?.selectedTheme

    override fun learnedNorth() = data?.learnedNorth?: 0

    override fun learnedSouth() = data?.learnedSouth?: 0

    override fun isThemeInDarkMode() = data?.useDarkTheme?: true

}
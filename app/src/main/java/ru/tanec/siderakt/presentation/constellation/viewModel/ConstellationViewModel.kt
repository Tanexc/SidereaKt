package ru.tanec.siderakt.presentation.constellation.viewModel

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class ConstellationViewModel @Inject constructor(
    val settings: SettingsController
): ViewModel() {

    private val _imageCollapsedState: MutableState<Boolean> = mutableStateOf(false)
    val isImageCollapsed by _imageCollapsedState

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(getTheme(settings.theme()?: Theme.Default(), settings.isThemeInDarkMode()))
    val colorScheme by _colorScheme

    fun changeImageCollapsedState() {
        _imageCollapsedState.value = !_imageCollapsedState.value
    }

}
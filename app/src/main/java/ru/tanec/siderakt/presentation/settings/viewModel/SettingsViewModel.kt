package ru.tanec.siderakt.presentation.settings.viewModel


import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val setPersonalInfoUseCase: SetInfoUseCase,
    val settings: SettingsController
) : ViewModel() {

    fun changeTheme(theme: Theme) {
        viewModelScope.launch {
            setPersonalInfoUseCase(settings.data!!.copy(selectedTheme = theme))
        }
    }

    fun changeUseDarkTheme() {
        viewModelScope.launch {
            setPersonalInfoUseCase(settings.data!!.copy(useDarkTheme = !settings.isThemeInDarkMode()))
        }
    }
}
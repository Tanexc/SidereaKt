package ru.tanec.siderakt.presentation.main.viewModel


import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.Screen
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    val settings: SettingsController
) : ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Catalog)
    val currentScreen by _currentScreen

    private val _topAppBar: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)
    val topAppBar by _topAppBar

    init {
        getPersonalInfoUseCase().onEach {
            settings.updateSettingsData(it)
        }.launchIn(viewModelScope)
    }

    fun screenChanged(screen: Screen) {
        _currentScreen.value = screen
    }

    fun setTopAppBar(topAppBar: (@Composable () -> Unit)?) {
        _topAppBar.value = topAppBar
    }
}
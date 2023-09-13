package ru.tanexc.siderakt.presentation.main.viewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import ru.tanexc.siderakt.domain.model.Screen
import ru.tanexc.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    val settings: SettingsController
) : ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Catalog)
    val currentScreen by _currentScreen

    private val _dialogState: MutableState<DialogState?> = mutableStateOf(null)
    val dialogState by _dialogState

    init {
        getPersonalInfoUseCase().onEach {
            settings.updateSettingsData(it)
        }.launchIn(viewModelScope)
    }

    fun screenChanged(screen: Screen) {
        _currentScreen.value = screen
    }

    fun hideDialog() {
        _dialogState.value = null
    }

    fun showDialog(dialog: DialogState) {
        _dialogState.value = dialog
    }
}
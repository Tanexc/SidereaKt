package ru.tanec.siderakt.presentation.settings.viewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tanec.siderakt.core.util.DialogState
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val setPersonalInfoUseCase: SetInfoUseCase,
    val settings: SettingsController
) : ViewModel() {

    private val _dialogState: MutableState<DialogState?> = mutableStateOf(null)
    val dialogState by _dialogState

    private val _uri: MutableState<String?> = mutableStateOf(null)
    val uri by _uri

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

    fun hideDialog() {
        _dialogState.value = null
    }

    fun showDialog(dialog: DialogState) {
        _dialogState.value = dialog
    }

    fun setUri(uri: String) {
        _uri.value = uri
    }

}
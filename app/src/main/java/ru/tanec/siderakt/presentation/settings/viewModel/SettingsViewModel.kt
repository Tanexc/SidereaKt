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
import ru.tanec.siderakt.core.util.Scheme
import ru.tanec.siderakt.data.utils.SettingsValues
import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val setPersonalInfoUseCase: SetInfoUseCase
) : ViewModel() {

    private val _theme: MutableState<Scheme> = mutableStateOf(SettingsValues.sidereaScheme.value)
    val appTheme by _theme

    private val _useDarkTheme: MutableState<Boolean> = mutableStateOf(SettingsValues.sidereaUseDarkTheme.value)
    val useDarkTheme by _useDarkTheme

    private val _personalInfo: MutableState<PersonalInformation?> = mutableStateOf(SettingsValues.sPersonalInformation.value)
    val personalInfo by _personalInfo

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(getTheme(appTheme, useDarkTheme))
    val colorScheme by _colorScheme

    init {

        getPersonalInfoUseCase().onEach {
            _theme.value = Scheme.getScheme(it.selectedTheme)
            _useDarkTheme.value = it.useDarkTheme
            _personalInfo.value = it
            _colorScheme.value = getTheme(colorScheme = appTheme, useDarkTheme = useDarkTheme)

        }.launchIn(viewModelScope)

    }

    fun changeTheme(theme: Scheme) {
        SettingsValues.sidereaScheme.value = theme
        viewModelScope.launch {
            setPersonalInfoUseCase(personalInfo!!.copy(selectedTheme = theme.id))
        }
    }

    fun changeUseDarkTheme() {
        SettingsValues.sidereaUseDarkTheme.value = !useDarkTheme
        viewModelScope.launch {
            setPersonalInfoUseCase(personalInfo!!.copy(useDarkTheme = !useDarkTheme))
        }
    }

    fun setColorScheme(colorScheme: ColorScheme) {
        _colorScheme.value = colorScheme
    }

}
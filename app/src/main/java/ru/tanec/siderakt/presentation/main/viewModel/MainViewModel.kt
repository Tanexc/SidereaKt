package ru.tanec.siderakt.presentation.main.viewModel

import android.util.Log
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.tanec.siderakt.core.util.Scheme
import ru.tanec.siderakt.core.util.Scheme.Companion.getScheme
import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.model.Screen
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val setPersonalInfoUseCase: SetInfoUseCase
) : ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Profile)
    val currentScreen by _currentScreen

    private val _buttonValue: MutableState<Int> = mutableIntStateOf(0)
    val buttonValue by _buttonValue

    private val _theme: MutableState<Scheme> = mutableStateOf(Scheme.Default())
    val appTheme by _theme

    private val _useDarkTheme: MutableState<Boolean> = mutableStateOf(true)
    val useDarkTheme by _useDarkTheme

    private val _personalInfo: MutableState<PersonalInformation?> = mutableStateOf(null)
    val personalInfo by _personalInfo

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(getTheme(appTheme, useDarkTheme))
    val colorScheme by _colorScheme


    init {

        getPersonalInfoUseCase().onEach {
            Log.i("cum", "CUM")
            _theme.value = getScheme(it.selectedTheme)
            _useDarkTheme.value = it.useDarkTheme
            _personalInfo.value = it
            _colorScheme.value = getTheme(colorScheme = appTheme, useDarkTheme = useDarkTheme)


        }.launchIn(viewModelScope)

    }

    fun screenChanged(screen: Screen) {
        _currentScreen.value = screen
    }
}
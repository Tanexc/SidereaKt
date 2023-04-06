package ru.tanec.siderakt.presentation.app.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tanec.siderakt.common.Scheme
import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetThemeUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetUseDarkThemeUseCase
import ru.tanec.siderakt.domain.use_case.personal_use_case.SetInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    getThemeUseCase: GetThemeUseCase,
    getUseDarkThemeUseCase: GetUseDarkThemeUseCase
): ViewModel() {

    val _theme: MutableState<Scheme> = mutableStateOf(Scheme.getScheme(getThemeUseCase()))
    val _useDarkThemeUseCase: MutableState<Boolean> = mutableStateOf(getUseDarkThemeUseCase())
    val _personalInfo: MutableState<PersonalInformation> = mutableStateOf(getPersonalInfoUseCase())

    val selectedTheme = _theme.value
    val useDarkThemeUseCase = _useDarkThemeUseCase.value
    val personalInfo = _personalInfo.value

    init {
        _theme.value = Scheme.getScheme(getThemeUseCase())
        _useDarkThemeUseCase.value = getUseDarkThemeUseCase()
        _personalInfo.value = getPersonalInfoUseCase()
    }

}
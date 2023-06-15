package ru.tanec.siderakt.data.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.tanec.siderakt.core.util.Scheme
import ru.tanec.siderakt.domain.model.PersonalInformation

object SettingsValues {

    val sidereaScheme: MutableState<Scheme> = mutableStateOf(Scheme.Default())
    val sidereaUseDarkTheme: MutableState<Boolean> = mutableStateOf(true)

    val sPersonalInformation: MutableState<PersonalInformation?> = mutableStateOf(null)

    fun updateValues(
        scheme: Scheme? = null,
        useDarkTheme: Boolean? = null,
        personalInformation: PersonalInformation? = null
    ) {
        sidereaScheme.value = scheme?: sidereaScheme.value
        sidereaUseDarkTheme.value = useDarkTheme?: sidereaUseDarkTheme.value
        sPersonalInformation.value = personalInformation?: sPersonalInformation.value

    }
}
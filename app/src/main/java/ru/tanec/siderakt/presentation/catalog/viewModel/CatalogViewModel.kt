package ru.tanec.siderakt.presentation.catalog.viewModel

import android.util.Log
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.tanec.siderakt.core.util.Scheme
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.data.utils.SettingsValues
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetAllConstellationsUseCase
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetConstellationById
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    getConstellationById: GetConstellationById,
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val getAllConstellationsUseCase: GetAllConstellationsUseCase
) : ViewModel() {

    private val _constellationListState: MutableState<State<List<Constellation>?>> =
        mutableStateOf(State.Loading(emptyList()))
    val constellationListState by _constellationListState

    private val _colorScheme: MutableState<ColorScheme> = mutableStateOf(
        getTheme(
            SettingsValues.sidereaScheme.value,
            SettingsValues.sidereaUseDarkTheme.value
        )
    )
    val colorScheme by _colorScheme

    private val _searchString: MutableState<String> = mutableStateOf("")
    val searchString by _searchString

    init {
        getAllConstellationsUseCase().onEach {
            _constellationListState.value = it
        }.launchIn(viewModelScope)

        getPersonalInfoUseCase().onEach {
            _colorScheme.value = getTheme(
                colorScheme = Scheme.getScheme(it.selectedTheme),
                useDarkTheme = it.useDarkTheme
            )

        }.launchIn(viewModelScope)
    }

    fun updateSearchString(value: String) {
        _searchString.value = value
    }
}
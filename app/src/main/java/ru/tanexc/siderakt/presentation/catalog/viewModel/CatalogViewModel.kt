package ru.tanexc.siderakt.presentation.catalog.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.decapitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.use_case.constellation_use_case.GetAllConstellationsUseCase
import ru.tanexc.siderakt.domain.use_case.constellation_use_case.GetConstellationById
import ru.tanexc.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    getConstellationById: GetConstellationById,
    getAllConstellationsUseCase: GetAllConstellationsUseCase,
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    val settings: SettingsController
) : ViewModel() {

    private val _constellationListState: MutableState<State<List<Constellation>?>> =
        mutableStateOf(State.Loading(emptyList()))
    val constellationListState by _constellationListState

    private val _currentConstellation: MutableState<Constellation?> = mutableStateOf(null)
    val currentConstellation: Constellation? by _currentConstellation

    private val _searchString: MutableState<String> = mutableStateOf("")
    val searchString by _searchString


    init {
        getAllConstellationsUseCase().onEach {
            _constellationListState.value = it
        }.launchIn(viewModelScope)
    }

    fun updateSearchString(value: String) {
        _searchString.value = value.replaceFirstChar { it.lowercase(Locale.ROOT) }
    }

    fun updateCurrentConstellation(value: Constellation?) {
        _currentConstellation.value = value
    }
}
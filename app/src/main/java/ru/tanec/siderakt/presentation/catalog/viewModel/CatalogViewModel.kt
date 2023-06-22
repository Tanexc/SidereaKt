package ru.tanec.siderakt.presentation.catalog.viewModel

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.tanec.siderakt.core.util.Theme
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.model.interfaces.SettingsController
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetAllConstellationsUseCase
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetConstellationById
import ru.tanec.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import ru.tanec.siderakt.presentation.ui.theme.getTheme
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

    private val _searchString: MutableState<String> = mutableStateOf("")
    val searchString by _searchString


    init {
        getAllConstellationsUseCase().onEach {
            _constellationListState.value = it
        }.launchIn(viewModelScope)
    }

    fun updateSearchString(value: String) {
        _searchString.value = value
    }
}
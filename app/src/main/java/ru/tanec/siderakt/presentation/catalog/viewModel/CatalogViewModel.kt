package ru.tanec.siderakt.presentation.catalog.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tanec.siderakt.common.State
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetAllConstellationsUseCase
import ru.tanec.siderakt.domain.use_case.constellation_use_case.GetConstellationById
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    getConstellationById: GetConstellationById,
    private val getAllConstellationsUseCase: GetAllConstellationsUseCase
): ViewModel() {

    val _constellationList: MutableState<List<Constellation>> = mutableStateOf(emptyList())
    val constellationList: List<Constellation> = _constellationList.value

    init {
        viewModelScope.launch {
            _constellationList.value = when(val state = getAllConstellationsUseCase()) {
                is State.Success -> state.data?: emptyList()
                else -> emptyList()
            }
        }

        //TODO("Load image bitmap")

    }

    fun updateList() {
        viewModelScope.launch {
            _constellationList.value = when(val state = getAllConstellationsUseCase()) {
                is State.Success -> state.data?: emptyList()
                else -> emptyList()
            }
        }
    }
}
package ru.tanexc.siderakt.presentation.constellation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import javax.inject.Inject

@HiltViewModel
class ConstellationViewModel @Inject constructor(
    val settings: SettingsController
): ViewModel() {

    private val _imageCollapsedState: MutableState<Boolean> = mutableStateOf(true)
    val isImageCollapsed by _imageCollapsedState

    fun changeImageCollapsedState() {
        _imageCollapsedState.value = !_imageCollapsedState.value
    }

}
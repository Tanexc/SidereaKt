package ru.tanexc.siderakt.presentation.main.viewModel


import android.graphics.BitmapFactory
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
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import retrofit2.Retrofit
import ru.tanexc.siderakt.core.util.state.DialogState
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.domain.interfaces.SettingsController
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.model.Screen
import ru.tanexc.siderakt.domain.use_case.constellation_use_case.EditConstellationUseCase
import ru.tanexc.siderakt.domain.use_case.constellation_use_case.GetAllConstellationsUseCase
import ru.tanexc.siderakt.domain.use_case.personal_use_case.GetPersonalInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getPersonalInfoUseCase: GetPersonalInfoUseCase,
    getAllConstellationsUseCase: GetAllConstellationsUseCase,
    editConstellationUseCase: EditConstellationUseCase,
    val retrofit: Retrofit,
    val settings: SettingsController
) : ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.Catalog)
    val currentScreen by _currentScreen

    private val _dialogState: MutableState<DialogState?> = mutableStateOf(null)
    val dialogState by _dialogState

    init {
        getPersonalInfoUseCase().onEach {
            settings.updateSettingsData(it)
        }.launchIn(viewModelScope)

        viewModelScope.launch(Dispatchers.IO) {
            getAllConstellationsUseCase().collect {
                when (it) {
                    is State.Success -> {
                        for (item in it.data?: emptyList()) {
                            if (item.imageCache == null) {
                                runCatching {
                                    val bytes = retrofit.callFactory().newCall(Request(item.imageURL.toHttpUrl())).execute().body.bytes()
                                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                    editConstellationUseCase(item.copy(imageCache = bitmap))
                                }

                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    fun screenChanged(screen: Screen) {
        _currentScreen.value = screen
    }

    fun hideDialog() {
        _dialogState.value = null
    }

    fun showDialog(dialog: DialogState) {
        _dialogState.value = dialog
    }
}
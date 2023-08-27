package ru.tanec.siderakt.presentation.test.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tanec.siderakt.domain.interfaces.SettingsController
import ru.tanec.siderakt.domain.model.TestItem
import ru.tanec.siderakt.domain.use_case.test_use_case.GetTestUseCase
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getTestUseCase: GetTestUseCase,
    val settngsController: SettingsController
): ViewModel() {
    private val _enableTimer: MutableState<Boolean> = mutableStateOf(false)
    val enableTimer: Boolean by _enableTimer

    private val _testData: MutableState<List<TestItem>> = mutableStateOf(emptyList())
    val testData by _testData



}
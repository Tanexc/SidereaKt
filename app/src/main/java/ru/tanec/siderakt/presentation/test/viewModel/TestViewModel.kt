package ru.tanec.siderakt.presentation.test.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.tanec.siderakt.core.util.state.State
import ru.tanec.siderakt.core.util.state.TestState
import ru.tanec.siderakt.domain.interfaces.SettingsController
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.model.TestItem
import ru.tanec.siderakt.domain.use_case.test_use_case.GetTestUseCase
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getTestUseCase: GetTestUseCase,
    val settngsController: SettingsController
) : ViewModel() {
    private val _enableTimer: MutableState<Boolean> = mutableStateOf(false)
    val enableTimer: Boolean by _enableTimer

    private var timer: Job? = null

    private val _timerTime: MutableState<Long> = mutableLongStateOf(900)
    val timerTime: Long by _timerTime

    private val _testData: MutableState<List<TestItem>?> = mutableStateOf(null)
    val testData by _testData

    private val _testState: MutableState<TestState> = mutableStateOf(TestState.NotStarted)
    val testState: TestState by _testState

    private val _isSnackBarVisible: MutableState<Boolean> = mutableStateOf(false)
    val isSnackBarVisible: Boolean by _isSnackBarVisible

    fun updateEnableTimer() {
        _enableTimer.value = !enableTimer
    }

    fun startTest(
        questionCount: Int,
        notLearned: Boolean,
        north: Boolean,
        south: Boolean,
        equatorial: Boolean
    ) {

        _testState.value = TestState.Started

        viewModelScope
            .launch(Dispatchers.IO) {
                getTestUseCase(
                    questionCount,
                    notLearned,
                    north,
                    south,
                    equatorial
                ).collect {
                    when(it) {
                        is State.Success -> {
                            _testData.value = it.data
                            startTimer()
                        }
                        else -> {}
                    }
                }
            }

    }

    fun endTest() {
        _testState.value = TestState.Ended
        endTimer()
        TODO("create editConstellationUseCase")
    }

    fun closeTest() {
        _testState.value = TestState.NotStarted
    }

    fun showSnackBar() {
        viewModelScope.launch(Dispatchers.IO) {
            _isSnackBarVisible.value = true
            delay(3000)
            _isSnackBarVisible.value = false
        }
    }

    private fun startTimer() {
        _timerTime.value = 800
        timer = viewModelScope.launch(Dispatchers.Default) {

            while(timerTime > 0) {
                delay(1000)
                _timerTime.value -= 1
            }

            endTimer()
        }
    }

    private fun endTimer() {
        timer?.cancel(null)
    }

    fun setItemAnswer(value: Constellation, index: Int) {
        _testData.value = _testData.value?.mapIndexed { ind, testItem -> if (ind == index) testItem.copy(answer = value) else testItem }
    }

}
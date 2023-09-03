package ru.tanexc.siderakt.domain.use_case.test_use_case

import kotlinx.coroutines.flow.Flow
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.domain.model.TestItem
import ru.tanexc.siderakt.domain.repository.TestRepository
import javax.inject.Inject

class GetTestUseCase @Inject constructor(
    private val repository: TestRepository
) {

    operator fun invoke(
        questionCount: Int,
        notLearned: Boolean,
        north: Boolean = false,
        south: Boolean = false,
        equatorial: Boolean = false
    ): Flow<State<List<TestItem>>> = repository.getTest(questionCount, notLearned, north, south, equatorial)

}
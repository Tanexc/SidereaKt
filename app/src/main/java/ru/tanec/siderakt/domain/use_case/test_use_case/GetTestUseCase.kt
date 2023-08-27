package ru.tanec.siderakt.domain.use_case.test_use_case

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.domain.model.TestItem
import ru.tanec.siderakt.domain.repository.TestRepository
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
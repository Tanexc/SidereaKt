package ru.tanexc.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.domain.model.TestItem

interface TestRepository {

    fun getTest(
        questionsCount: Int,
        notLearned: Boolean,
        north: Boolean,
        south: Boolean,
        equatorial: Boolean
    ): Flow<State<List<TestItem>>>

}
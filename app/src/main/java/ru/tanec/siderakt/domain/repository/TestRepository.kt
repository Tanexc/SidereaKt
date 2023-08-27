package ru.tanec.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.domain.model.TestItem

interface TestRepository {

    fun getTest(
        questionsCount: Int,
        notLearned: Boolean,
        north: Boolean,
        south: Boolean,
        equatorial: Boolean
    ): Flow<State<List<TestItem>>>

}
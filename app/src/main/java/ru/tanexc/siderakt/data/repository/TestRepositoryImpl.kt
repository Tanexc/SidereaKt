package ru.tanexc.siderakt.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.data.local.dao.ConstellationDao
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.model.TestItem
import ru.tanexc.siderakt.domain.repository.TestRepository
import javax.inject.Inject
import kotlin.random.Random

class TestRepositoryImpl @Inject constructor(
    private val constellationDao: ConstellationDao
) : TestRepository {

    override fun getTest(
        questionsCount: Int,
        notLearned: Boolean,
        north: Boolean,
        south: Boolean,
        equatorial: Boolean
    ): Flow<State<List<TestItem>>> = flow {
        emit(State.Loading())
        try {
            val catalog: List<Constellation> = constellationDao
                .getConstellationList()
                .map { it.asDomain() }
            var data: MutableList<Constellation> = catalog
                .filter {
                    if (north) {
                        it.hemisphere == 1
                    } else {
                        false
                    } ||
                            if (south) {
                                it.hemisphere == 2
                            } else {
                                false
                            } ||
                            if (equatorial) {
                                it.hemisphere == 0
                            } else {
                                false
                            }
                } as MutableList

            if (notLearned) {
                data = data.filter { !it.learned } as MutableList
            }

            while (data.size > questionsCount) {
                data.removeAt(Random.nextInt(questionsCount))
            }

            emit(State.Success(data.map { question ->
                TestItem(
                    question,
                    (catalog.filter { it !in data }.shuffled().subList(0, 3) + question).shuffled()
                )
            }))
        } catch (e: Exception) {
            emit(State.Error(message = e.message ?: "error"))
        }
    }
}
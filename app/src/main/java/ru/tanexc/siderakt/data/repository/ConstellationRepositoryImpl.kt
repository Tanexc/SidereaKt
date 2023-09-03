package ru.tanexc.siderakt.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.data.local.dao.ConstellationDao
import ru.tanexc.siderakt.domain.model.Constellation
import ru.tanexc.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class ConstellationRepositoryImpl @Inject constructor(
    private val constellationDao: ConstellationDao

) : ConstellationRepository {
    override fun getConstellationList(
    ): Flow<State<List<Constellation>?>> = flow {
        emit(State.Loading())
        try {
            emit(State.Success(constellationDao.getConstellationList().map { it.asDomain() }))
        } catch(e: Exception) {
            emit(State.Error(message=e.message))
        }
    }

    override suspend fun getConstellationById(id: Long): Flow<State<Constellation?>> = flow {
        try {
            emit(State.Success(constellationDao.getConstellationById(id).asDomain()))
        } catch(e: Exception) {
            emit(State.Error(message=e.message))
        }
    }

    override suspend fun editConstellation(data: Constellation) = constellationDao.editConstellation(data.asDatabaseEntity())

}
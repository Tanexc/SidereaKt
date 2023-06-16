package ru.tanec.siderakt.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.data.local.dao.ConstellationDao
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class ConstellationRepositoryImpl @Inject constructor(
    private val constellationDao: ConstellationDao

) : ConstellationRepository {
    override fun getConstellationList(
    ): Flow<State<List<Constellation>?>> = flow {
        Log.i("cum", "cock")
        emit(State.Loading())
        try {
            Log.i("cum", "POT")
            emit(State.Success(constellationDao.getConstellationList().map { it.asDomain() }))
        } catch(e: Exception) {
            Log.i("cum", "cringe")
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

    override suspend fun <T> insertConstellationList(data: List<Constellation>): State<T> {
        TODO("Not yet implemented")
    }
}
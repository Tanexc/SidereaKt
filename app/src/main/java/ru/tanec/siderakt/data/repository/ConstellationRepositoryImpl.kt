package ru.tanec.siderakt.data.repository

import ru.tanec.siderakt.common.State
import ru.tanec.siderakt.data.local.dao.ConstellationDao
import ru.tanec.siderakt.domain.model.Constellation
import ru.tanec.siderakt.domain.repository.ConstellationRepository
import javax.inject.Inject

class ConstellationRepositoryImpl @Inject constructor(
    private val constellationDao: ConstellationDao
) : ConstellationRepository {
    override suspend fun getConstellationList(
    ): State<List<Constellation>> {
        runCatching {
            return State.Success(constellationDao.getConstellationList().map { it.asDomain() })
        }
        return State.Error()

    }

    override suspend fun getConstellationById(id: Long): State<Constellation?> {
        runCatching {
            return State.Success(constellationDao.getConstellationById(id).asDomain())
        }
        return State.Error()
    }

    override suspend fun <T> insertConstellationList(data: List<Constellation>): State<T> {
        TODO("Not yet implemented")
    }
}
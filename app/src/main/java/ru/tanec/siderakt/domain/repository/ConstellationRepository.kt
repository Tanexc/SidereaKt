package ru.tanec.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.core.util.State
import ru.tanec.siderakt.domain.model.Constellation

interface ConstellationRepository {
    fun getConstellationList(): Flow<State<List<Constellation>?>>

    suspend fun <T> insertConstellationList(data: List<Constellation>): State<T>

    suspend fun getConstellationById(id: Long): Flow<State<Constellation?>>
}
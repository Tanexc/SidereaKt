package ru.tanec.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.core.util.state.State
import ru.tanec.siderakt.domain.model.Constellation

interface ConstellationRepository {
    fun getConstellationList(): Flow<State<List<Constellation>?>>

    suspend fun getConstellationById(id: Long): Flow<State<Constellation?>>

    suspend fun editConstellation(data: Constellation)
}
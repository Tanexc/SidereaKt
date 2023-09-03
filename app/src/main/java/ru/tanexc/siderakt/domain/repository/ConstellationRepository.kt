package ru.tanexc.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanexc.siderakt.core.util.state.State
import ru.tanexc.siderakt.domain.model.Constellation

interface ConstellationRepository {
    fun getConstellationList(): Flow<State<List<Constellation>?>>

    suspend fun getConstellationById(id: Long): Flow<State<Constellation?>>

    suspend fun editConstellation(data: Constellation)
}
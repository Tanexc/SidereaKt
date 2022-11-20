package ru.tanec.siderakt.domain.repository

import ru.tanec.siderakt.common.State
import ru.tanec.siderakt.domain.model.Constellation

interface ConstellationRepository {
    suspend fun getConstellationList(limit: Int?, offset: Int?): State<List<Constellation>>

    suspend fun <T> insertConstellationList(data: List<Constellation>): State<T>

    suspend fun getConstellationById(id: Long): State<Constellation?>
}
package ru.tanexc.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanexc.siderakt.domain.model.SettingsData

interface PersonalInfoRepository {
    fun getInfo(): Flow<SettingsData>

    suspend fun setInformation(info: SettingsData)
}
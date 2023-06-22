package ru.tanec.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.domain.model.SettingsData

interface PersonalInfoRepository {
    fun getInfo(): Flow<SettingsData>

    suspend fun setInformation(info: SettingsData)
}
package ru.tanec.siderakt.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanec.siderakt.domain.model.PersonalInformation

interface PersonalInfoRepository {
    fun getInfo(): Flow<PersonalInformation>

    suspend fun setInformation(info: PersonalInformation)
}
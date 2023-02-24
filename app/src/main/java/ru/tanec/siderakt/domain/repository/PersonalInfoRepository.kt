package ru.tanec.siderakt.domain.repository

import ru.tanec.siderakt.domain.model.PersonalInformation

interface PersonalInfoRepository {
    fun getTheme(): Int

    fun getUseDarkTheme(): Boolean

    fun getInfo(): PersonalInformation

    suspend fun setInformation(info: PersonalInformation)
}
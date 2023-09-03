package ru.tanexc.siderakt.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.tanexc.siderakt.data.local.dao.PersonalInfoDao
import ru.tanexc.siderakt.domain.model.SettingsData
import ru.tanexc.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
    private val personalInfoDao: PersonalInfoDao,
) : PersonalInfoRepository {
    override fun getInfo(): Flow<SettingsData> = personalInfoDao.getInfo().map {it.asDomain()}

    override suspend fun setInformation(info: SettingsData) =
        personalInfoDao.setInfo(info.asDatabaseEntity())
}

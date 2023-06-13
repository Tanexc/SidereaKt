package ru.tanec.siderakt.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.tanec.siderakt.data.local.dao.PersonalInfoDao
import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
    private val personalInfoDao: PersonalInfoDao,
) : PersonalInfoRepository {
    override fun getInfo(): Flow<PersonalInformation> = personalInfoDao.getInfo().map {it.asDomain()}

    override suspend fun setInformation(info: PersonalInformation) =
        personalInfoDao.setInfo(info.asDatabaseEntity())
}

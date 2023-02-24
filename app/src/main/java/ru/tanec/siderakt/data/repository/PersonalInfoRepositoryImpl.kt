package ru.tanec.siderakt.data.repository

import ru.tanec.siderakt.data.local.dao.PersonalInfoDao
import ru.tanec.siderakt.domain.model.PersonalInformation
import ru.tanec.siderakt.domain.repository.PersonalInfoRepository
import javax.inject.Inject

class PersonalInfoRepositoryImpl @Inject constructor(
    private val personalInfoDao: PersonalInfoDao,
) : PersonalInfoRepository {
    override fun getTheme(): Int = personalInfoDao.getInfo().selectedTheme

    override fun getUseDarkTheme(): Boolean = personalInfoDao.getInfo().useDarkTheme

    override fun getInfo(): PersonalInformation = personalInfoDao.getInfo().asDomain()

    override suspend fun setInformation(info: PersonalInformation) =
        personalInfoDao.setInfo(info.asDataBaseEntity())
}
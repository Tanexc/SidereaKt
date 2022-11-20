package ru.tanec.siderakt.data.repository

import ru.tanec.siderakt.data.local.dao.ThemeDao
import ru.tanec.siderakt.domain.model.Theme
import ru.tanec.siderakt.domain.repository.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val themeDao: ThemeDao,
) : ThemeRepository {

    override fun getTheme(): Theme = themeDao.getTheme().asDomain()

    override suspend fun setTheme(theme: Theme) = themeDao.setTheme(theme.asDataBaseEntity())
}
package ru.tanec.siderakt.domain.repository

import ru.tanec.siderakt.domain.model.Theme

interface ThemeRepository {
    fun getTheme(): Theme

    suspend fun setTheme(theme: Theme)
}